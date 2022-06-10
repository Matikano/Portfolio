package com.gmail.matikano9.todoapp.presentation.todo_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
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
                    delay(500)
                    getSearchedTasks()
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