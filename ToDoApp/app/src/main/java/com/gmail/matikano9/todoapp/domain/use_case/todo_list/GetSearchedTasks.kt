package com.gmail.matikano9.todoapp.domain.use_case.todo_list

import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchedTasks @Inject constructor(
    private val repository: ToDoRepository
){

    operator fun invoke(searchQuery: String = ""): Flow<List<ToDoTask>>
        = repository.searchTasks(searchQuery)
}