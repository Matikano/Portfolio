package com.gmail.matikano9.todoapp.presentation.todo_task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.presentation.todo_task.components.*
import com.gmail.matikano9.todoapp.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

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

