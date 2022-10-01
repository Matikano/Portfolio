package com.gmail.matikano9.todoapp.data.repository

import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class FakeToDoRepository : ToDoRepository {

    private val tasks = mutableListOf<ToDoTask>()

    override val getAllTasks: Flow<List<ToDoTask>>
        get() = flow { emit(tasks) }
    override val sortByPriorityLow: Flow<List<ToDoTask>>
        get() = flow { emit(tasks.sortedBy { toDoTask -> toDoTask.priority })}
    override val sortByPriorityHigh: Flow<List<ToDoTask>>
        get() = flow { emit(tasks.sortedByDescending { toDoTask -> toDoTask.priority })}

    override fun getTask(taskId: Int): Flow<ToDoTask> = flow {
        tasks.find {
            it.id == taskId
        }?.let { emit(it) }
    }


    override fun searchTasks(searchQuery: String): Flow<List<ToDoTask>>  = flow {
        emit(tasks.filter {
            it.title.contains(searchQuery) || it.description.contains(searchQuery)
        })
    }

    override suspend fun addTask(toDoTask: ToDoTask) {
        tasks.add(toDoTask)
    }

    override suspend fun updateTask(toDoTask: ToDoTask) {
        tasks[tasks.indexOf(tasks.find { it.id == toDoTask.id })] = toDoTask
    }

    override suspend fun deleteTask(toDoTask: ToDoTask) {
        tasks.remove(toDoTask)
    }

    override suspend fun deleteAllTasks() {
        tasks.clear()
    }
}