package com.gmail.matikano9.todoapp.domain.use_case.todo_list

import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSortedTasks @Inject constructor(
    private val repository: ToDoRepository
) {

    operator fun invoke(priority: Priority): Flow<List<ToDoTask>>
        = when(priority) {
            Priority.High -> repository.sortByPriorityHigh
            Priority.Low -> repository.sortByPriorityLow
            else -> repository.getAllTasks
        }
}