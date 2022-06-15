package com.gmail.matikano9.todoapp.presentation.todo_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.presentation.components.DisplayAlertDialog
import com.gmail.matikano9.todoapp.presentation.components.PriorityItem
import com.gmail.matikano9.todoapp.presentation.todo_list.ToDoListEvent
import com.gmail.matikano9.todoapp.presentation.todo_list.ToDoListViewModel
import com.gmail.matikano9.todoapp.presentation.ui.theme.SPACE_SMALL
import com.gmail.matikano9.todoapp.presentation.ui.theme.TOP_APP_BAR_HEIGHT
import com.gmail.matikano9.todoapp.presentation.ui.theme.ToDoAppTheme


@Composable
fun ListAppBar(
    viewModel: ToDoListViewModel
) {
    val state = viewModel.state


    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_all_tasks_title),
        message = stringResource(id = R.string.delete_all_tasks_message),
        openDialog = state.dialogOpen,
        closeDialog = {
            viewModel.onEvent(ToDoListEvent.OnCloseDialog)
        },
        onYesClicked = {
            viewModel.onEvent(ToDoListEvent.OnDeleteAllTaskConfirmed)
        }
    )

    if(state.searchAppBar){
        SearchAppBar(
            text = state.searchQuery,
            onTextChange = {
                newSearchQuery -> viewModel.onEvent(ToDoListEvent.OnSearchQueryChanged(newSearchQuery))
            },
            onCloseClicked = {
                viewModel.onEvent(ToDoListEvent.OnCloseActionClicked)
            }
        )
    } else {
        DefaultListAppBar(
            onSearchClicked = {
                viewModel.onEvent(ToDoListEvent.OnSearchActionClicked)
            },
            onSortClicked = { priority ->
                viewModel.onEvent(ToDoListEvent.OnSortClicked(priority))
            },
            onDeleteAllClicked = {
                viewModel.onEvent(ToDoListEvent.OnDeleteAllTasksActionClicked)
            }
        )
    }

}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit
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
                onDeleteAllClicked = onDeleteAllClicked
            )
        }
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {onTextChange(it)},
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.alpha(ContentAlpha.disabled)
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.onSurface,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon),
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.alpha(ContentAlpha.disabled)
                )
            },
            trailingIcon = {
                IconButton(onClick = { onCloseClicked() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_icon),
                        tint = MaterialTheme.colors.onSurface,
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.onSurface,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit
){
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteClicked = onDeleteAllClicked)
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
            modifier = Modifier.background(color = MaterialTheme.colors.background),
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
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false)}

    IconButton(onClick = {expanded = true}) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(id = R.string.actions_more)
        )
        DropdownMenu(
            modifier = Modifier.background(color = MaterialTheme.colors.background),
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
                Spacer(modifier = Modifier.width(SPACE_SMALL))
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
            onDeleteAllClicked = {}
        )
    }
}

@Preview
@Composable
fun SearchAppBarPreview() {
    ToDoAppTheme {
        SearchAppBar(
            text = "Search",
            onTextChange = {},
            onCloseClicked = {},
        )
    }
}