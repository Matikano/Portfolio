package com.gmail.matikano9.todoapp.presentation.todo_list


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.presentation.destinations.ToDoTaskScreenDestination
import com.gmail.matikano9.todoapp.presentation.todo_list.components.DefaultListAppBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun ToDoListScreen(
    navigator: DestinationsNavigator,
    viewModel: ToDoListViewModel = hiltViewModel()
) {


    val state = viewModel.state

    Scaffold(
        topBar = {
            DefaultListAppBar(
                onSearchClicked = {/*TODO:*/},
                onSortClicked = {},
                onDeleteClicked = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigator.navigate(ToDoTaskScreenDestination())
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_task)
                )
            }
        }

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Greeting(name = "World")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
