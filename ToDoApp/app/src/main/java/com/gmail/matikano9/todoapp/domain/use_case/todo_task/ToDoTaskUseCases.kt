package com.gmail.matikano9.todoapp.domain.use_case.todo_task

import com.gmail.matikano9.todoapp.domain.use_case.common.DeleteTask
import javax.inject.Inject

data class ToDoTaskUseCases @Inject constructor(
    val addTask: AddTask,
    val updateTask: UpdateTask,
    val deleteTask: DeleteTask
)
