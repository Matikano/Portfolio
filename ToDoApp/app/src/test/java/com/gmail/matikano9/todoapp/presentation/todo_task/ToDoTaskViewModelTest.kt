package com.gmail.matikano9.todoapp.presentation.todo_task

import com.gmail.matikano9.todoapp.data.repository.FakeToDoRepository
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.TaskType
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import com.gmail.matikano9.todoapp.domain.use_case.common.DeleteTask
import com.gmail.matikano9.todoapp.domain.use_case.todo_list.*
import com.gmail.matikano9.todoapp.domain.use_case.todo_task.AddTask
import com.gmail.matikano9.todoapp.domain.use_case.todo_task.ToDoTaskUseCases
import com.gmail.matikano9.todoapp.domain.use_case.todo_task.UpdateTask
import com.gmail.matikano9.todoapp.domain.use_case.validation.*
import com.gmail.matikano9.todoapp.util.Constants
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
class ToDoTaskViewModelTest{
    private lateinit var testViewModel: ToDoTaskViewModel
    private lateinit var repository: ToDoRepository

    private var tasks = mutableListOf<ToDoTask>()

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        repository = FakeToDoRepository()

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

        val toDoTaskUseCases = ToDoTaskUseCases(
            AddTask(repository),
            UpdateTask(repository),
            DeleteTask(repository)
        )

        val validationUseCases = ValidationUseCases(
            ValidateTitle(),
            ValidateDescription(),
            ValidateDate(),
            ValidateTime()
        )

        testViewModel = ToDoTaskViewModel(
            toDoTaskUseCases,
            validationUseCases,
        )

    }

    @After
    fun teardown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `onEvent DeleteClicked, state dialogOpen is true`(){
        testViewModel.onEvent(ToDoTaskEvent.OnDeleteClicked)
        assertThat(testViewModel.state.dialogOpen).isTrue()
    }

    @Test
    fun `onEvent DescriptionChanged to invalid value, state updates correctly`(){
        testViewModel.onEvent(ToDoTaskEvent.OnDescriptionChanged(""))
        assertThat(testViewModel.state.description).isEmpty()
        assertThat(testViewModel.state.descriptionError).isEqualTo(Constants.Validation.DESCRIPTION_ERROR)
    }

    @Test
    fun `onEvent DescriptionChanged to valid value, state updates correctly`(){
        testViewModel.onEvent(ToDoTaskEvent.OnDescriptionChanged(Constants.Test.TODO_DESCRIPTION))
        assertThat(testViewModel.state.description).isEqualTo(Constants.Test.TODO_DESCRIPTION)
        assertThat(testViewModel.state.descriptionError).isNull()
    }

    @Test
    fun `onEvent TitleChanged to invalid value, state updates correctly`(){
        testViewModel.onEvent(ToDoTaskEvent.OnTitleChanged(""))
        assertThat(testViewModel.state.title).isEmpty()
        assertThat(testViewModel.state.titleError).isEqualTo(Constants.Validation.TITLE_ERROR)
    }

    @Test
    fun `onEvent TitleChanged to valid value, state updates correctly`(){
        testViewModel.onEvent(ToDoTaskEvent.OnTitleChanged(Constants.Test.TODO_TITLE))
        assertThat(testViewModel.state.title).isEqualTo(Constants.Test.TODO_TITLE)
        assertThat(testViewModel.state.titleError).isNull()
    }

    @Test
    fun `onEvent PriorityChanged, state updates correctly`(){
        val priority = Priority.values().random()
        testViewModel.onEvent(ToDoTaskEvent.OnPriorityChanged(priority))
        assertThat(testViewModel.state.priority).isEqualTo(priority)
    }

    @Test
    fun `onEvent TypeChanged, state updates correctly`(){
        val type = TaskType.values().random()
        testViewModel.onEvent(ToDoTaskEvent.OnTypeChanged(type))
        assertThat(testViewModel.state.type).isEqualTo(type)
    }

    @Test
    fun `onEvent CloseDialog, state dialogOpen is false`(){
        testViewModel.onEvent(ToDoTaskEvent.OnCloseDialog)
        assertThat(testViewModel.state.dialogOpen).isFalse()
    }




}