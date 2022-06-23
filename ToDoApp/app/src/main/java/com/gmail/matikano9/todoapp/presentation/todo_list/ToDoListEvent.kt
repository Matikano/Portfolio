package com.gmail.matikano9.todoapp.presentation.todo_list

import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.ToDoTask

sealed class ToDoListEvent {
    data class OnSearchQueryChanged(val query: String): ToDoListEvent()
    data class OnSortClicked(val priority: Priority): ToDoListEvent()
    data class OnToDoTaskClicked(val toDoTask: ToDoTask): ToDoListEvent()
    data class OnSwipeToDelete(val toDoTask: ToDoTask) : ToDoListEvent()


    object OnFabAddClicked: ToDoListEvent()
    object OnSearchActionClicked: ToDoListEvent()
    object OnCloseActionClicked: ToDoListEvent()
    object OnDeleteAllTasksActionClicked: ToDoListEvent()
    object OnDeleteAllTaskConfirmed: ToDoListEvent()
    object OnCloseDialog: ToDoListEvent()
}
