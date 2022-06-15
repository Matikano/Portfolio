package com.gmail.matikano9.todoapp.presentation.todo_list

import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.ToDoTask

data class ToDoListState(
    val toDoList: List<ToDoTask> = emptyList(),
    val searchQuery: String = "",
    val searchAppBar: Boolean = false,
    val sortPriority: Priority = Priority.None,
    val dialogOpen: Boolean = false
)