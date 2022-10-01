package com.gmail.matikano9.todoapp.presentation.todo_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import com.gmail.matikano9.todoapp.domain.use_case.todo_task.ToDoTaskUseCases
import com.gmail.matikano9.todoapp.domain.use_case.validation.*
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
    private val useCases: ToDoTaskUseCases,
    private val validationUseCases: ValidationUseCases,
    savedStateHandle: SavedStateHandle? = null
): ViewModel(){

    var toDoTask: ToDoTask? = null

    init {
        toDoTask = savedStateHandle?.get(NAV_ARG_TODO_TASK)
    }

    var state by mutableStateOf(ToDoTaskState(toDoTask = toDoTask))

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent  = _uiEvent.receiveAsFlow()

    val validateTitle = validationUseCases.validateTitle
    val validateDescription = validationUseCases.validateDescription
    val validateDate = validationUseCases.validateDate
    val validateTime = validationUseCases.validateTime

    fun onEvent(event: ToDoTaskEvent){
        when(event){
            is ToDoTaskEvent.OnDeleteClicked -> {
                state = state.copy(
                    dialogOpen = true
                )
            }

            is ToDoTaskEvent.OnDeleteConfirmed -> {
                viewModelScope.launch {
                    useCases.deleteTask(state.toDoTask!!)
                }

                sendUiEvent(UiEvent.PopBackStack)
            }

            is ToDoTaskEvent.OnDescriptionChanged -> {
                val valResult = validateDescription(event.description)

                state = state.copy(
                    description = event.description,
                    descriptionError = valResult.errorMessage
                )

            }

            is ToDoTaskEvent.OnTitleChanged -> {
                val valResult = validateTitle(event.title)

                state = state.copy(
                    title = event.title,
                    titleError = valResult.errorMessage
                )
            }

            is ToDoTaskEvent.OnPriorityChanged -> {
                state = state.copy(priority = event.priority)
            }

            is ToDoTaskEvent.OnTypeChanged -> {
                state = state.copy(type = event.type)
            }

            is ToDoTaskEvent.OnDueDateChanged -> {
                val valResult = validateDate(event.dueDateString, event.dueDate)

                state = state.copy(
                    dueDate = event.dueDate,
                    dueDateString = event.dueDateString,
                    dueDateError = valResult.errorMessage
                )
            }

            is ToDoTaskEvent.OnDueTimeChanged -> {
                val valResult = validateTime(event.dueTimeString)

                state = state.copy(
                    dueTime = event.dueTime,
                    dueTimeString = event.dueTimeString,
                    dueTimeError = valResult.errorMessage
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
                            useCases.updateTask(toDoTask = toDoTask!!)
                        else
                            useCases.addTask(toDoTask = toDoTask!!)
                    }

                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        val titleResult = validateTitle(state.title)
        val descriptionResult = validateDescription(state.description)
        val dateResult = validateDate(state.dueDateString, state.dueDate!!)
        val timeResult = validateTime(state.dueTimeString)

        val hasError = listOf(
            titleResult,
            descriptionResult,
            dateResult,
            timeResult
        ).any { result -> !result.successful }

        if(hasError){
            state = state.copy(
                titleError = titleResult.errorMessage,
                descriptionError = descriptionResult.errorMessage,
                dueDateError =  dateResult.errorMessage,
                dueTimeError = timeResult.errorMessage
            )
        }

        return !hasError
    }



    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}


