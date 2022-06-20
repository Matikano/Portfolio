package com.gmail.matikano9.todoapp.presentation.todotask

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import com.gmail.matikano9.todoapp.util.Constants.Navigation.NAV_ARG_TODO_TASK
import com.gmail.matikano9.todoapp.util.Extensions.toMillis
import com.gmail.matikano9.todoapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ToDoTaskViewModel  @Inject constructor(
    private val repository: ToDoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    var toDoTask: ToDoTask? = null

    init {
        toDoTask = savedStateHandle[NAV_ARG_TODO_TASK]
    }

    var state by mutableStateOf(ToDoTaskState(toDoTask = toDoTask))

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent  = _uiEvent.receiveAsFlow()

    fun onEvent(event: ToDoTaskEvent){
        when(event){
            is ToDoTaskEvent.OnDeleteClicked -> {
                state = state.copy(
                    dialogOpen = true
                )
            }

            is ToDoTaskEvent.OnDeleteConfirmed -> {
                viewModelScope.launch {
                    repository.deleteTask(state.toDoTask!!)
                }

                sendUiEvent(UiEvent.PopBackStack)
            }

            is ToDoTaskEvent.OnDescriptionChanged -> {
                state = state.copy(description = event.description)
            }

            is ToDoTaskEvent.OnTitleChanged -> {
                state = state.copy(title = event.title)
            }

            is ToDoTaskEvent.OnPriorityChanged -> {
                state = state.copy(priority = event.priority)
            }

            is ToDoTaskEvent.OnTypeChanged -> {
                state = state.copy(type = event.type)
            }

            is ToDoTaskEvent.OnDueDateChanged -> {
                state = state.copy(
                    dueDate = event.dueDate,
                    dueDateString = event.dueDateString
                )
            }

            is ToDoTaskEvent.OnDueTimeChanged -> {
                state = state.copy(
                    dueTime = event.dueTime,
                    dueTimeString = event.dueTimeString
                )
            }

            is ToDoTaskEvent.OnCloseDialog -> {
                state = state.copy(
                    dialogOpen = false
                )
            }

            is ToDoTaskEvent.OnNavigateBackClicked -> {
                sendUiEvent(UiEvent.PopBackStack)
            }

            is ToDoTaskEvent.OnConfirmClicked -> {
                if(validateFields()){
                    toDoTask = LocalDateTime.of(state.dueDate, state.dueTime).toMillis()?.let { dueTimeInMillis ->
                        ToDoTask(
                            id = state.id,
                            title = state.title,
                            description = state.description,
                            type = state.type,
                            priority = state.priority,
                            dueTimeInMillis = dueTimeInMillis
                        )
                    }

                    viewModelScope.launch {
                        if(state.editing)
                            repository.updateTask(toDoTask = toDoTask!!)
                        else
                            repository.addTask(toDoTask = toDoTask!!)
                    }

                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        //TODO: implement fields validation
        return true
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}


