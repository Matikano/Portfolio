package com.gmail.matikano9.todoapp.presentation.todotask

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
    val description: String = toDoTask?.description ?: "",
    val type: TaskType = toDoTask?.type ?: TaskType.Other,
    val priority: Priority = toDoTask?.priority ?: Priority.None,
    val dueTimeInMillis: Long = toDoTask?.dueTimeInMillis ?: 0L,
    val dueDate: LocalDate? = toDoTask?.dueLocalDateTime()?.toLocalDate() ?: LocalDate.MIN,
    val dueTime: LocalTime? = toDoTask?.dueLocalDateTime()?.toLocalTime() ?: LocalTime.MIN,
    val dueTimeString: String = toDoTask?.dueLocalDateTime()?.toTimeString() ?: "",
    val dueDateString: String = toDoTask?.dueLocalDateTime()?.toDateString() ?: "",
    val dialogOpen: Boolean = false
)
