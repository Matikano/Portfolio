package com.gmail.matikano9.todoapp.presentation.todo_task

import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.TaskType
import com.gmail.matikano9.todoapp.util.Extensions.toDateString
import com.gmail.matikano9.todoapp.util.Extensions.toTimeString
import java.time.LocalDate
import java.time.LocalTime

sealed class ToDoTaskEvent {
    object OnConfirmClicked: ToDoTaskEvent()
    object OnDeleteClicked: ToDoTaskEvent()
    object OnCloseDialog: ToDoTaskEvent()
    object OnDeleteConfirmed: ToDoTaskEvent()
    object OnNavigateBackClicked: ToDoTaskEvent()

    data class OnTitleChanged(val title: String): ToDoTaskEvent()
    data class OnDescriptionChanged(val description: String): ToDoTaskEvent()
    data class OnPriorityChanged(val priority: Priority): ToDoTaskEvent()
    data class OnTypeChanged(val type: TaskType): ToDoTaskEvent()


    data class OnDueDateChanged(
        val dueDate: LocalDate,
        val dueDateString: String = dueDate.toDateString()
    ): ToDoTaskEvent()

    data class OnDueTimeChanged(
        val dueTime: LocalTime,
        val dueTimeString: String = dueTime.toTimeString()
    ): ToDoTaskEvent()

}