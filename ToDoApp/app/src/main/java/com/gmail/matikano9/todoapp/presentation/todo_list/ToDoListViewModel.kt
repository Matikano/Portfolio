package com.gmail.matikano9.todoapp.presentation.todo_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val repository: ToDoRepository
): ViewModel(){

    var state by mutableStateOf(ToDoListState())

    private var searchJob: Job? = null

    init{
        getAllTasks()
    }

    fun onEvent(event: ToDoListEvent){
        when(event){
            is ToDoListEvent.OnSearchQueryChanged ->{
                state = state.copy(
                    searchQuery = event.query
                )
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(100)
                    getSearchedTasks()
                }
            }

            is ToDoListEvent.OnSearchActionClicked -> {
                state = state.copy(searchAppBar = true)
            }

            is ToDoListEvent.OnCloseActionClicked -> {
                state = state.copy(
                    searchAppBar = false,
                    searchQuery = ""
                )
                getAllTasks()
            }

            is ToDoListEvent.OnDeleteAllTasksActionClicked -> {
                state = state.copy(
                    dialogOpen = true,
                )
            }

            is ToDoListEvent.OnCloseDialog -> {
                state = state.copy(
                    dialogOpen = false
                )
            }

            is ToDoListEvent.OnDeleteAllTaskConfirmed -> {
                viewModelScope.launch {
                    repository.deleteAllTasks()
                    getAllTasks()
                }
            }

            is ToDoListEvent.OnSortClicked -> {
                state = state.copy(
                    sortPriority = event.priority
                )

                viewModelScope.launch {
                    when(state.sortPriority){
                        Priority.High -> repository.sortByPriorityHigh.collect{
                            tasks -> state = state.copy(toDoList = tasks)
                        }
                        Priority.Low -> repository.sortByPriorityLow.collect{
                            tasks -> state = state.copy(toDoList = tasks)
                        }
                        else -> repository.getAllTasks.collect{
                            tasks -> state = state.copy(toDoList = tasks)
                        }
                    }
                }
            }
        }

    }

    private fun getAllTasks() {
        viewModelScope.launch {
            repository.getAllTasks.collect { tasks->
                state = state.copy(toDoList = tasks)
            }
        }
    }

    private fun getSearchedTasks(
        query: String = state.searchQuery.lowercase(),
    ){
        viewModelScope.launch {
            repository.searchTasks(query).collect { tasks->
                state = state.copy(toDoList = tasks)
            }
        }
    }
}