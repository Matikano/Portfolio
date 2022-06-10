package com.gmail.matikano9.todoapp.presentation.todo_list.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.presentation.components.PriorityItem
import com.gmail.matikano9.todoapp.presentation.ui.theme.SPACE_MEDIUM
import com.gmail.matikano9.todoapp.presentation.ui.theme.ToDoAppTheme

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.default_list_app_bar))
        },
        backgroundColor = MaterialTheme.colors.primary,

        modifier = Modifier
            .fillMaxWidth(),

        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteClicked
            )
        }
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
){
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(
        onClick = { onSearchClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_action),
            tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
){

    var expanded by remember { mutableStateOf(false)}

    IconButton(onClick = {expanded = true}) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_tasks)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            DropdownMenuItem(onClick = {
                expanded = false
                onSortClicked(Priority.Low)
            }) {
                PriorityItem(priority = Priority.Low)
            }

            DropdownMenuItem(onClick = {
                expanded = false
                onSortClicked(Priority.High)
            }) {
                PriorityItem(priority = Priority.High)
            }

            DropdownMenuItem(onClick = {
                expanded = false
                onSortClicked(Priority.None)
            }) {
                PriorityItem(priority = Priority.None)
            }

        }
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false)}

    IconButton(onClick = {expanded = true}) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(id = R.string.actions_more)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            DropdownMenuItem(onClick = {
                expanded = false
                onDeleteClicked()
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(id = R.string.delete_tasks)
                )
                Spacer(modifier = Modifier.width(SPACE_MEDIUM))
                Text(text = stringResource(id = R.string.delete_tasks))
            }

        }
    }
}

@Preview
@Composable
fun DefaultListAppBarPreview() {
    ToDoAppTheme {
        DefaultListAppBar(
            onSearchClicked = {},
            onSortClicked = {},
            onDeleteClicked = {}
        )
    }
}