package com.gmail.matikano9.todoapp.presentation.todotask

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.inputmethodservice.Keyboard
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.presentation.components.PriorityItem
import com.gmail.matikano9.todoapp.presentation.todotask.components.PriorityDropDown
import com.gmail.matikano9.todoapp.presentation.todotask.components.ToDoTaskAppBar
import com.gmail.matikano9.todoapp.presentation.todotask.components.ToDoTaskTextField
import com.gmail.matikano9.todoapp.presentation.todotask.components.TypeDropDown
import com.gmail.matikano9.todoapp.presentation.ui.theme.*
import com.gmail.matikano9.todoapp.util.Extensions.toDateString
import com.gmail.matikano9.todoapp.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun ToDoTaskScreen(
    toDoTask: ToDoTask? = null,
    navigator: DestinationsNavigator,
    viewModel: ToDoTaskViewModel = hiltViewModel()
) {

    val state = viewModel.state

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.ShowSnackBar ->{

                }
                is UiEvent.PopBackStack -> {
                    navigator.popBackStack()
                }
                is UiEvent.Navigate ->  {
                    navigator.navigate(event.destination)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            ToDoTaskAppBar(
                state = state,
                onEvent = { event ->
                    viewModel.onEvent(event)
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(ToDoTaskEvent.OnConfirmClicked)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.add_task),
                    modifier = Modifier.size(32.dp)
                )
            }
        },

        content = {
            ToDoTaskContent(
                state = state,
                onEvent = { event ->
                    viewModel.onEvent(event)
                },
            )
        }
    )
}

@Composable
fun ToDoTaskContent(
    state: ToDoTaskState,
    onEvent: (ToDoTaskEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(PADDING_MEDIUM)
            .fillMaxSize()
    ) {


        ToDoTaskTextField(
            value = state.title,
            label = stringResource(id = R.string.title),
            onTextChanged = {
                title -> onEvent(ToDoTaskEvent.OnTitleChanged(title))
            }
        )
        Spacer(modifier = Modifier.height(SPACE_MEDIUM))
        ToDoTaskTextField(
            value = state.description,
            label = stringResource(id = R.string.description),
            onTextChanged = {
                description -> onEvent(ToDoTaskEvent.OnDescriptionChanged(description))
            },
            singleLine = false,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(SPACE_MEDIUM))
        PriorityDropDown(
            priority = state.priority,
            onEvent = onEvent,
        )
        Spacer(modifier = Modifier.height(SPACE_MEDIUM))
        TypeDropDown(
            type = state.type,
            onEvent = onEvent
        )
        Spacer(modifier = Modifier.height(SPACE_MEDIUM))
        Row(
            modifier = Modifier
            .fillMaxWidth()
        ) {

            val calendar = Calendar.getInstance()

            val year: Int  = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

            val hour: Int = calendar.get(Calendar.HOUR)
            val minute: Int = calendar.get(Calendar.MINUTE)


            val datePickerDialog = DatePickerDialog(
                LocalContext.current,
                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    onEvent(ToDoTaskEvent.OnDueDateChanged(
                            LocalDate.of(year, month+1, dayOfMonth)
                        )
                    )
                }, year, month, day
            )

            val timePickerDialog = TimePickerDialog(
                LocalContext.current,
                { _: TimePicker, hour: Int, minute: Int ->
                    onEvent(ToDoTaskEvent.OnDueTimeChanged(
                            LocalTime.of(hour, minute)
                        )
                    )

                }, hour, minute, true
            )

            ToDoTaskTextField(
                modifier = Modifier
                    .weight(1f)
                    .clickable(enabled = false) {},
                value = state.dueDateString,
                label = stringResource(id = R.string.dueDate),
                onTextChanged = { dueDateString ->
                    onEvent(ToDoTaskEvent.OnDueDateChanged(
                            LocalDate.parse(dueDateString)
                        )
                    )
                },
                singleLine = true,
                leadingIcon = {
                   IconButton(
                       onClick = {
                           datePickerDialog.show()
                       }
                   ) {
                       Icon(
                           imageVector = Icons.Default.CalendarToday,
                           contentDescription = null
                       )
                   }
                }
            )
            Spacer(modifier = Modifier.width(SPACE_MEDIUM))
            ToDoTaskTextField(
                modifier = Modifier
                    .weight(1f)
                    .clickable(enabled = false){},
                value = state.dueTimeString,
                label = stringResource(id = R.string.dueTime),
                onTextChanged = { dueTimeString ->
                    onEvent(ToDoTaskEvent.OnDueTimeChanged(
                            LocalTime.parse(dueTimeString)
                        )
                    )
                },
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        onClick = {
                            timePickerDialog.show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null
                        )
                    }

                }
            )
        }

    }
}
