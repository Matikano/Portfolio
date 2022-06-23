package com.gmail.matikano9.todoapp.presentation.todo_list


import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.presentation.destinations.SplashScreenDestination
import com.gmail.matikano9.todoapp.presentation.destinations.ToDoListScreenDestination
import com.gmail.matikano9.todoapp.presentation.todo_list.components.ListAppBar
import com.gmail.matikano9.todoapp.presentation.todo_list.components.SwipeBackground
import com.gmail.matikano9.todoapp.presentation.todo_list.components.ToDoListItem
import com.gmail.matikano9.todoapp.presentation.ui.theme.PADDING_VERY_SMALL
import com.gmail.matikano9.todoapp.presentation.ui.theme.SWIPE_TO_DISMISS_BASE_ANGLE
import com.gmail.matikano9.todoapp.presentation.ui.theme.SWIPE_TO_DISMISS_MAX_ANGLE
import com.gmail.matikano9.todoapp.presentation.ui.theme.SWIPE_TO_DISMISS_THRESHOLD
import com.gmail.matikano9.todoapp.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
        ) {
            items(
                items = state.toDoList,
                key = { task ->
                    task.id
                }
            ) { toDoTask ->

                val dismissState = rememberDismissState()
                val dismissDirection = dismissState.dismissDirection
                val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
                if(isDismissed && dismissDirection == DismissDirection.EndToStart){
                    viewModel.onEvent(ToDoListEvent.OnSwipeToDelete(toDoTask))
                }

                val degrees by animateFloatAsState(
                    if (dismissState.targetValue == DismissValue.Default)
                        SWIPE_TO_DISMISS_BASE_ANGLE
                    else
                        SWIPE_TO_DISMISS_MAX_ANGLE
                )

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = {
                        FractionalThreshold(SWIPE_TO_DISMISS_THRESHOLD)
                    },
                    background = {
                        SwipeBackground(degrees = degrees)
                    },
                    dismissContent = {
                        ToDoListItem(toDoTask = toDoTask) { task ->
                            viewModel.onEvent(ToDoListEvent.OnToDoTaskClicked(task))
                        }
                    }
                )
            }
        }
    }

}

