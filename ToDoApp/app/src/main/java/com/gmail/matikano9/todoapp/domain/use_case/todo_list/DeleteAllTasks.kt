package com.gmail.matikano9.todoapp.domain.use_case.todo_list

import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import javax.inject.Inject

class DeleteAllTasks @Inject constructor(
    private val repository: ToDoRepository
) {

    suspend operator fun invoke() {
        repository.deleteAllTasks()
    }
}