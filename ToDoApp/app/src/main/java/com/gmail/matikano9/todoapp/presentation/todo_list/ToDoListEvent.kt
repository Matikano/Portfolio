package com.gmail.matikano9.todoapp.presentation.todo_list

sealed class ToDoListEvent {
    data class OnSearchQueryChanged(val query: String): ToDoListEvent()
}
