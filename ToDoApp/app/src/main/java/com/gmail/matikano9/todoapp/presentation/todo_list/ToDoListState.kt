package com.gmail.matikano9.todoapp.presentation.todo_list

import com.gmail.matikano9.todoapp.domain.model.ToDoTask

data class ToDoListState(
    val toDoList: List<ToDoTask> = emptyList(),
    val searchQuery: String = ""
)