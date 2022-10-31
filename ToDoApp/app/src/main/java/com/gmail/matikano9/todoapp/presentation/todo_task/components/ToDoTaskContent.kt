package com.gmail.matikano9.todoapp.presentation.todo_task.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.presentation.todo_task.ToDoTaskEvent
import com.gmail.matikano9.todoapp.presentation.todo_task.ToDoTaskState
import com.gmail.matikano9.todoapp.presentation.ui.theme.PADDING_MEDIUM
import com.gmail.matikano9.todoapp.presentation.ui.theme.SPACE_MEDIUM
import com.gmail.matikano9.todoapp.util.Extensions.noRippleClickable
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

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
            isError = state.titleError != null,
            onTextChanged = {
                    title -> onEvent(ToDoTaskEvent.OnTitleChanged(title))
            }
        )
        if(state.titleError != null){
            ValidationErrorText(
                modifier = Modifier
                    .fillMaxWidth(),
                errorText = state.titleError
            )
        }
        Spacer(modifier = Modifier.height(SPACE_MEDIUM))
        ToDoTaskTextField(
            value = state.description,
            label = stringResource(id = R.string.description),
            isError = state.descriptionError != null,
            onTextChanged = {
                    description -> onEvent(ToDoTaskEvent.OnDescriptionChanged(description))
            },
            singleLine = false,
            maxLines = 2
        )
        if(state.descriptionError != null){
            ValidationErrorText(
                modifier = Modifier
                    .fillMaxWidth(),
                errorText = state.descriptionError
            )
        }
        Spacer(modifier = Modifier.height(SPACE_MEDIUM))
        PriorityDropDown(
            priority = state.priority,
            onEvent = onEvent,
        )
        Spacer(modifier = Modifier.height(SPACE_MEDIUM))
        TypeDropDown(
            type = state.type,
            onEvent = onEvent,
        )
        Spacer(modifier = Modifier.height(SPACE_MEDIUM))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top

        ) {

            val calendar = Calendar.getInstance()

            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

            val hour: Int = calendar.get(Calendar.HOUR)
            val minute: Int = calendar.get(Calendar.MINUTE)


            val datePickerDialog = DatePickerDialog(
                LocalContext.current,
                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    onEvent(
                        ToDoTaskEvent.OnDueDateChanged(
                            LocalDate.of(year, month + 1, dayOfMonth)
                        )
                    )
                }, year, month, day
            )

            val timePickerDialog = TimePickerDialog(
                LocalContext.current,
                { _: TimePicker, hour: Int, minute: Int ->
                    onEvent(
                        ToDoTaskEvent.OnDueTimeChanged(
                            LocalTime.of(hour, minute)
                        )
                    )

                }, hour, minute, true
            )
            ToDoTaskTextField(
                modifier = Modifier
                    .weight(1f)
                    .noRippleClickable {
                        datePickerDialog.show()
                    },
                enabled = false,
                value = state.dueDateString,
                label = stringResource(id = R.string.dueDate),
                isError = state.dueDateError != null,
                onTextChanged = { dueDateString ->
                    onEvent(
                        ToDoTaskEvent.OnDueDateChanged(
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
                    .noRippleClickable {
                        timePickerDialog.show()
                    },
                enabled = false,
                value = state.dueTimeString,
                label = stringResource(id = R.string.dueTime),
                isError = state.dueTimeError != null,
                onTextChanged = { dueTimeString ->
                    onEvent(
                        ToDoTaskEvent.OnDueTimeChanged(
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if(state.dueDateError != null){
                ValidationErrorText(
                    modifier = Modifier
                        .weight(1f),
                    errorText = state.dueDateError
                )
            } else{
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.width(SPACE_MEDIUM))
            if(state.dueTimeError != null){
                ValidationErrorText(
                    modifier = Modifier
                        .weight(1f),
                    errorText = state.dueTimeError
                )
            } else{
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}