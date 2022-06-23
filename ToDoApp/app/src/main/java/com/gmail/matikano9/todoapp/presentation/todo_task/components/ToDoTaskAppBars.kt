package com.gmail.matikano9.todoapp.presentation.todo_task.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.presentation.components.DisplayAlertDialog
import com.gmail.matikano9.todoapp.presentation.todo_task.ToDoTaskEvent
import com.gmail.matikano9.todoapp.presentation.todo_task.ToDoTaskState


@Composable
fun ToDoTaskAppBar(
    state: ToDoTaskState,
    onEvent: (ToDoTaskEvent) -> Unit
) {

    val toDoTask: ToDoTask? = state.toDoTask

    DisplayAlertDialog(
        title = toDoTask?.title?.let {
                title -> stringResource(id = R.string.delete_task_dialog, title)
        } ?: "",
        message = toDoTask?.title?.let {
                message -> stringResource(id = R.string.delete_task_message, message)
        } ?: "",
        openDialog = state.dialogOpen,
        closeDialog = {
            onEvent(ToDoTaskEvent.OnCloseDialog)
        },
        onYesClicked = {
            onEvent(ToDoTaskEvent.OnDeleteConfirmed)
        }
    )


    TopAppBar(
        navigationIcon = {
            BackAction(
                editing = state.editing,
                onEvent = onEvent
            )
        },
        title = {
            Text(
                text = toDoTask?.title ?: stringResource(id = R.string.add_task),
                color = MaterialTheme.colors.onPrimary
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            DeleteAction(
                editing = state.editing,
                onEvent = onEvent,
            )
            AddEditAction(onEvent = onEvent)
        }
    )
}

@Composable
fun BackAction(
    editing: Boolean,
    onEvent: (ToDoTaskEvent) -> Unit
) {
    IconButton(onClick =  {
        onEvent(ToDoTaskEvent.OnNavigateBackClicked)
    }) {
        Icon(
            imageVector = if (editing) Icons.Default.ArrowBack else Icons.Default.Close,
            contentDescription = stringResource(id = R.string.navigate_back),
        )
    }
}

@Composable
fun AddEditAction(
    onEvent: (ToDoTaskEvent) -> Unit
) {
    IconButton(
        onClick = {
            onEvent(ToDoTaskEvent.OnConfirmClicked)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(id = R.string.action_add_edit)
        )
    }
}

@Composable
fun DeleteAction(
    editing: Boolean = false,
    onEvent: (ToDoTaskEvent) -> Unit
) {
    if(editing) {
        IconButton(
            onClick = {
                onEvent(ToDoTaskEvent.OnDeleteClicked)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete_task)
            )
        }
    }
}
