package com.gmail.matikano9.todoapp.presentation.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gmail.matikano9.todoapp.presentation.ui.theme.PADDING_LARGE
import com.gmail.matikano9.todoapp.presentation.ui.theme.PADDING_MEDIUM
import com.gmail.matikano9.todoapp.presentation.ui.theme.ToDoAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun ListScreen(
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier =  Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "ToDo Tasks")
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

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    ToDoAppTheme {
        DestinationsNavHost(navGraph = NavGraphs.root)
    }
}