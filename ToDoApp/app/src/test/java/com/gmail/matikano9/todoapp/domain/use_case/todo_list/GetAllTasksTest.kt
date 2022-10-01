package com.gmail.matikano9.todoapp.domain.use_case.todo_list

import com.gmail.matikano9.todoapp.data.repository.FakeToDoRepository
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.TaskType
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import com.gmail.matikano9.todoapp.util.Constants
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAllTasksTest {

    private lateinit var repository: ToDoRepository
    private lateinit var getAllTasks: GetAllTasks

    private var tasks = mutableListOf<ToDoTask>()

    @Before
    fun setup(){
        repository = FakeToDoRepository()
        getAllTasks = GetAllTasks(repository)

        val priorities = Priority.values()
        val types = TaskType.values()

        for (i in 1..10){
            tasks.add(
                ToDoTask(
                    i,
                    if (i % 3 == 0) "${Constants.Test.TODO_TITLE_SEARCH_QUERY}${Constants.Test.TODO_TITLE}$i" else "${Constants.Test.TODO_TITLE}$i",
                    Constants.Test.TODO_DESCRIPTION + i,
                    priorities[i % priorities.size],
                    types[i % types.size],
                    0L
                )
            )
        }

        tasks.shuffle()

        runTest {
            tasks.forEach { repository.addTask(it) }
        }

    }

    @Test
    fun `get all tasks contains all from local list`() = runTest {
        val tasksFromRepository = repository.getAllTasks.first()
        assertThat(tasksFromRepository).containsExactlyElementsIn(tasks)
    }


}