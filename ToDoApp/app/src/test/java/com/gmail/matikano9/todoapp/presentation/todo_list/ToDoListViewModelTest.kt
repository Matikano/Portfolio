package com.gmail.matikano9.todoapp.presentation.todo_list

import com.gmail.matikano9.todoapp.data.repository.FakeToDoRepository
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.TaskType
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.use_case.common.DeleteTask
import com.gmail.matikano9.todoapp.domain.use_case.todo_list.*
import com.gmail.matikano9.todoapp.util.Constants
import com.gmail.matikano9.todoapp.util.Constants.Test.TODO_TITLE_SEARCH_QUERY
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class ToDoListViewModelTest {

    private lateinit var testViewModel: ToDoListViewModel

    private var tasks = mutableListOf<ToDoTask>()

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        val repository = FakeToDoRepository()

        val priorities = Priority.values()
        val types = TaskType.values()

        for (i in 1..10){
            tasks.add(
                ToDoTask(
                    i,
                    if (i % 3 == 0) "${TODO_TITLE_SEARCH_QUERY}${Constants.Test.TODO_TITLE}$i" else "${Constants.Test.TODO_TITLE}$i",
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

        testViewModel = ToDoListViewModel(
            ToDoListUseCases(
                DeleteTask(repository),
                DeleteAllTasks(repository),
                GetAllTasks(repository),
                GetSearchedTasks(repository),
                GetSortedTasks(repository)
            )
        )
    }

    @After
    fun teardown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `onEvent SearchTasksEvent, changes state correctly and filters out tasks`(){
        testViewModel.onEvent(ToDoListEvent.OnSearchQueryChanged(TODO_TITLE_SEARCH_QUERY))
        assertThat(testViewModel.state.searchQuery).isEqualTo(TODO_TITLE_SEARCH_QUERY)
        testViewModel.state.toDoList.forEach { task ->
            assertThat(task.title).contains(TODO_TITLE_SEARCH_QUERY)
        }
    }

    @Test
    fun `onEvent SearchActionClicked, state searchAppBar is true`(){
        testViewModel.onEvent(ToDoListEvent.OnSearchActionClicked)
        assertThat(testViewModel.state.searchAppBar).isTrue()
    }

    @Test
    fun `onEvent CloseActionClicked, state searchAppBar is false and searchQuery is empty`()  {
        testViewModel.onEvent(ToDoListEvent.OnCloseActionClicked)
        assertThat(testViewModel.state.searchAppBar).isFalse()
        assertThat(testViewModel.state.searchQuery).isEmpty()
    }

    @Test
    fun `onEvent DeleteAllActionsActionClicked, state dialogOpen is true`(){
        testViewModel.onEvent(ToDoListEvent.OnDeleteAllTasksActionClicked)
        assertThat(testViewModel.state.dialogOpen).isTrue()
    }

    @Test
    fun `onEvent CloseDialog, state dialogOpen is false`(){
        testViewModel.onEvent(ToDoListEvent.OnCloseDialog)
        assertThat(testViewModel.state.dialogOpen).isFalse()
    }

    @Test
    fun `onEvent DeleteAllTasksConfirmed, state task list is empty`(){
        testViewModel.onEvent(ToDoListEvent.OnDeleteAllTaskConfirmed)
        assertThat(testViewModel.state.toDoList).isEmpty()
    }

    @Test
    fun `onEvent Sort with priority High, state sortPriority is correct set and task list is in correct order`(){
        testViewModel.onEvent(ToDoListEvent.OnSortClicked(Priority.High))
        assertThat(testViewModel.state.sortPriority).isEqualTo(Priority.High)
        val tasks = testViewModel.state.toDoList
        for (i in 0 until tasks.size - 1){
            if (tasks[i].priority == Priority.None)
                continue
            assertThat(tasks[i].priority).isAtMost(tasks[i + 1].priority)
        }

    }

    @Test
    fun `onEvent Sort with priority Low, state sortPriority is correct set and task list is in correct order`(){
        testViewModel.onEvent(ToDoListEvent.OnSortClicked(Priority.Low))
        assertThat(testViewModel.state.sortPriority).isEqualTo(Priority.Low)
        val tasks = testViewModel.state.toDoList
        for (i in 0 until tasks.size - 1){
            if (tasks[i].priority == Priority.None)
                continue
            assertThat(tasks[i].priority).isAtLeast(tasks[i + 1].priority)
        }
    }

    @Test
    fun `onEvent SwipeToDelete clicked, state task list does not contain deleted task`(){
        val task = tasks.random()
        testViewModel.onEvent(ToDoListEvent.OnSwipeToDelete(task))
        assertThat(testViewModel.state.toDoList).doesNotContain(task)
    }

}