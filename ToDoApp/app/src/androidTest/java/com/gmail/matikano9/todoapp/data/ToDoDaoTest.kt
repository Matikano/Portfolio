package com.gmail.matikano9.todoapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.gmail.matikano9.todoapp.data.database.ToDoDao
import com.gmail.matikano9.todoapp.data.database.ToDoDatabase
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.TaskType
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.util.Constants
import com.gmail.matikano9.todoapp.util.Constants.Test.TEST_DATABASE_NAME
import com.gmail.matikano9.todoapp.util.Constants.Test.TODO_DESCRIPTION
import com.gmail.matikano9.todoapp.util.Constants.Test.TODO_TITLE_SEARCH_QUERY
import com.gmail.matikano9.todoapp.util.Constants.Test.TODO_TITLE
import com.gmail.matikano9.todoapp.util.Extensions.toMillis
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ToDoDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named(TEST_DATABASE_NAME)
    lateinit var database: ToDoDatabase
    private lateinit var dao: ToDoDao

    private lateinit var testTask: ToDoTask
    private val tasks = mutableListOf<ToDoTask>()

    @Before
    fun setup(){
        hiltRule.inject()
        dao = database.toDoDao

        testTask = ToDoTask(
            0,
            Constants.Test.TODO_TITLE,
            Constants.Test.TODO_DESCRIPTION,
            Priority.Low,
            TaskType.Other,
            0
        )

        val priorities = Priority.values()
        val types = TaskType.values()

        for (i in 1..10){
            tasks.add(
                ToDoTask(
                    i,
                    if (i % 3 == 0) "$TODO_TITLE_SEARCH_QUERY$TODO_TITLE$i" else "$TODO_TITLE$i",
                    TODO_DESCRIPTION + i,
                    priorities[i % priorities.size],
                    types[i % types.size],
                    0L
                )
            )
        }

        tasks.shuffle()

        runTest {
            tasks.forEach { dao.addTask(it) }
        }

    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun addToDoTask_tasksInDB() {
        runTest {
            val dbTasks = dao.getAllTasks().first()
            dbTasks.forEach {
                assertThat(tasks).contains(it)
            }
        }
    }

    @Test
    fun deleteToDoTask_taskDeletedFromDB() {
        runTest {
            var tasks = dao.getAllTasks().first()
            val taskToDelete = tasks[0]
            assertThat(tasks).contains(taskToDelete)
            dao.deleteTask(taskToDelete)
            tasks = dao.getAllTasks().first()
            assertThat(tasks).doesNotContain(taskToDelete)
        }
    }

    @Test
    fun sortByPriorityLow_correctOrder() = runTest {
        val tasks = dao.sortByPriorityLow().first()
        for (i in 0..tasks.size - 2){
            if(tasks[i + 1].priority == Priority.None)
                return@runTest
            assertThat(tasks[i].priority).isAtMost(tasks[i + 1].priority)
        }
    }

    @Test
    fun sortByPriorityHigh_correctOrder() = runTest {
        val tasks = dao.sortByPriorityHigh().first()
        for (i in 0..tasks.size - 2){
            if(tasks[i + 1].priority == Priority.None)
                return@runTest
            assertThat(tasks[i].priority).isAtLeast(tasks[i + 1].priority)
        }
    }

    @Test
    fun getTask_correct() = runTest {
        val task = tasks.random()
        val dbTask = dao.getTask(task.id).first()
        assertThat(dbTask).isEqualTo(task)
    }

    @Test
    fun getAllTasks_correct() = runTest {
        val dbTasks = dao.getAllTasks().first()
        assertThat(dbTasks).containsExactlyElementsIn(tasks)
    }

    @Test
    fun searchTasks_containsAllSearchedTasks() = runTest {
        val filteredTasks = dao.searchTasks(TODO_TITLE_SEARCH_QUERY).first()
        filteredTasks.forEach { task ->
            assertThat(task.title).contains(TODO_TITLE_SEARCH_QUERY)
        }
    }

    @Test
    fun deleteAllTasks_correct() = runTest {
        var tasks = dao.getAllTasks().first()
        assertThat(tasks).isNotEmpty()
        dao.deleteAllTasks()
        tasks = dao.getAllTasks().first()
        assertThat(tasks).isEmpty()
    }



}