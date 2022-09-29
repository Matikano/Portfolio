package com.gmail.matikano9.todoapp.domain.use_case.todo_task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gmail.matikano9.todoapp.data.repository.FakeToDoRepository
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.TaskType
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import com.gmail.matikano9.todoapp.util.Constants
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UpdateTaskTest {

    private lateinit var updateTask: UpdateTask
    private lateinit var repository: ToDoRepository

    private lateinit var toDoTask: ToDoTask

    @Before
    fun setup() {
        repository = FakeToDoRepository()
        updateTask = UpdateTask(repository)

        toDoTask = ToDoTask(
            1,
            Constants.Test.TODO_TITLE,
            Constants.Test.TODO_DESCRIPTION,
            Priority.Low,
            TaskType.Other,
            0
        )

        runBlocking {
            repository.addTask(toDoTask)
        }
    }

    @Test
    fun `update task successful`(){
        val updateTask = toDoTask.copy(title = Constants.Test.UPDATED_TODO_TITLE)

        runBlocking {
            val tasks = repository.getAllTasks.last()
            assertThat(tasks).contains(toDoTask)
            updateTask(updateTask)
            assertThat(tasks).contains(updateTask)
        }
    }
}