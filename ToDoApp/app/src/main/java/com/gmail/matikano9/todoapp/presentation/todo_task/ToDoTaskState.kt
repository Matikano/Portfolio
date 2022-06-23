package com.gmail.matikano9.todoapp.presentation.todo_task

import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.TaskType
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.util.Extensions.toDateString
import com.gmail.matikano9.todoapp.util.Extensions.toTimeString
import java.time.LocalDate
import java.time.LocalTime

data class ToDoTaskState(
    val toDoTask: ToDoTask? = null,
    val editing: Boolean = toDoTask != null,
    val id: Int = toDoTask?.id ?: 0,
    val title: String = toDoTask?.title ?: "",
    val titleError: String? = null,
    val description: String = toDoTask?.description ?: "",
    val descriptionError: String? = null,
    val type: TaskType = toDoTask?.type ?: TaskType.Other,
    val priority: Priority = toDoTask?.priority ?: Priority.None,
    val dueTimeInMillis: Long = toDoTask?.dueTimeInMillis ?: 0L,
    val dueDate: LocalDate? = toDoTask?.dueLocalDateTime()?.toLocalDate() ?: LocalDate.MIN,
    val dueDateError: String? = null,
    val dueTime: LocalTime? = toDoTask?.dueLocalDateTime()?.toLocalTime() ?: LocalTime.MIN,
    val dueTimeError: String? = null,
    val dueTimeString: String = toDoTask?.dueLocalDateTime()?.toTimeString() ?: "",
    val dueDateString: String = toDoTask?.dueLocalDateTime()?.toDateString() ?: "",
    val dialogOpen: Boolean = false
)
