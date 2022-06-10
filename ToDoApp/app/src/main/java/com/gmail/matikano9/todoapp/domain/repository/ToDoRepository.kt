package com.gmail.matikano9.todoapp.domain.repository

import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ToDoRepository{

    val getAllTasks: Flow<List<ToDoTask>>
    val sortByPriorityLow: Flow<List<ToDoTask>>
    val sortByPriorityHigh: Flow<List<ToDoTask>>

    fun getTask(taskId: Int): Flow<ToDoTask>
    fun searchTasks(searchQuery: String): Flow<List<ToDoTask>>

    suspend fun addTask(toDoTask: ToDoTask)
    suspend fun updateTask(toDoTask: ToDoTask)
    suspend fun deleteTask(toDoTask: ToDoTask)
    suspend fun deleteAllTasks()

}