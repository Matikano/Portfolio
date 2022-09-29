package com.gmail.matikano9.todoapp.domain.use_case.todo_task

import com.gmail.matikano9.todoapp.data.repository.FakeToDoRepository
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.TaskType
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import com.gmail.matikano9.todoapp.util.Constants.Test.TODO_DESCRIPTION
import com.gmail.matikano9.todoapp.util.Constants.Test.TODO_TITLE
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class AddTaskTest {

    private lateinit var addTask: AddTask
    private lateinit var repository: ToDoRepository

    @Before
    fun setup() {
        repository = FakeToDoRepository()
        addTask = AddTask(repository)
    }

    @Test
    fun `add task, exists in repository`(){
        val toDoTask = ToDoTask(
            1,
            TODO_TITLE,
            TODO_DESCRIPTION,
            Priority.Low,
            TaskType.Other,
            0
        )

        runBlocking {
            val tasks: List<ToDoTask> = repository.getAllTasks.last()
            assertThat(tasks).isEmpty()
            addTask(toDoTask)
            assertThat(tasks).contains(toDoTask)
        }

    }
}