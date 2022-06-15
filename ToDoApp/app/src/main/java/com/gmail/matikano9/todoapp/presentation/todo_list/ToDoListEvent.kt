package com.gmail.matikano9.todoapp.presentation.todo_list

import com.gmail.matikano9.todoapp.domain.model.Priority

sealed class ToDoListEvent {
    data class OnSearchQueryChanged(val query: String): ToDoListEvent()
    data class OnSortClicked(val priority: Priority): ToDoListEvent()

    object OnSearchActionClicked: ToDoListEvent()
    object OnCloseActionClicked: ToDoListEvent()
    object OnDeleteAllTasksActionClicked: ToDoListEvent()
    object OnDeleteAllTaskConfirmed: ToDoListEvent()
    object OnCloseDialog: ToDoListEvent()
}
