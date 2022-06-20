package com.gmail.matikano9.todoapp.presentation.todo_list


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.presentation.todo_list.components.ListAppBar
import com.gmail.matikano9.todoapp.presentation.todo_list.components.ToDoListItem
import com.gmail.matikano9.todoapp.presentation.ui.theme.PADDING_VERY_SMALL
import com.gmail.matikano9.todoapp.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RootNavGraph(start = true)
@Destination
@Composable
fun ToDoListScreen(
    navigator: DestinationsNavigator,
    viewModel: ToDoListViewModel = hiltViewModel()
) {

    val state = viewModel.state

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.ShowSnackBar ->{

                }
                is UiEvent.Navigate ->  {
                    navigator.navigate(event.destination)
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            ListAppBar(
                state,
                onEvent = { event ->
                    viewModel.onEvent(event)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(ToDoListEvent.OnFabAddClicked)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_task),
                    modifier = Modifier.size(32.dp)
                )
            }
        }

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = PADDING_VERY_SMALL)
        ){
          items(state.toDoList.size) { i ->
              val toDoTask = state.toDoList[i]
              ToDoListItem(toDoTask = toDoTask) { task ->
                  viewModel.onEvent(ToDoListEvent.OnToDoTaskClicked(task))
              }
          }
        }
    }
}

