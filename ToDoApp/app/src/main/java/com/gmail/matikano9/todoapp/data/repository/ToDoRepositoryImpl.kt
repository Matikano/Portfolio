package com.gmail.matikano9.todoapp.data.repository

import com.gmail.matikano9.todoapp.data.database.ToDoDao
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoRepositoryImpl  @Inject constructor(
    private val dao: ToDoDao
): ToDoRepository{

    override val getAllTasks: Flow<List<ToDoTask>> = dao.getAllTasks()
    override val sortByPriorityLow: Flow<List<ToDoTask>> = dao.sortByPriorityLow()
    override val sortByPriorityHigh: Flow<List<ToDoTask>> = dao.sortByPriorityHigh()

    override fun getTask(taskId: Int): Flow<ToDoTask> = dao.getTask(taskId = taskId)
    override fun searchTasks(searchQuery: String): Flow<List<ToDoTask>> = dao.searchTasks(searchQuery = searchQuery)

    override suspend fun addTask(toDoTask: ToDoTask) = dao.addTask(toDoTask = toDoTask)
    override suspend fun updateTask(toDoTask: ToDoTask) = dao.updateTask(toDoTask = toDoTask)
    override suspend fun deleteTask(toDoTask: ToDoTask)  = dao.deleteTask(toDoTask = toDoTask)
    override suspend fun deleteAllTasks() = dao.deleteAllTasks()

}