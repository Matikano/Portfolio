package com.gmail.matikano9.todoapp.domain.use_case.common

import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import javax.inject.Inject

class DeleteTask @Inject constructor(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(toDoTask: ToDoTask){
        repository.deleteTask(toDoTask)
    }
}