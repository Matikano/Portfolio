package com.gmail.matikano9.todoapp.presentation.todotask.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.presentation.components.PriorityItem
import com.gmail.matikano9.todoapp.presentation.todotask.ToDoTaskEvent
import com.gmail.matikano9.todoapp.presentation.ui.theme.CORNER_SHAPE_SMALL
import com.gmail.matikano9.todoapp.presentation.ui.theme.PADDING_MEDIUM

@Composable
fun PriorityDropDown(
    modifier : Modifier = Modifier,
    priority: Priority,
    onEvent: (ToDoTaskEvent) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }


    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = false, onClick = {}),
        readOnly = true,
        value = priority.name,
        onValueChange = { priorityName ->
            onEvent(ToDoTaskEvent.OnPriorityChanged(Priority.valueOf(priorityName)))
        },
        label = {
            Text(text = stringResource(id = R.string.priority))
        },
        shape = RoundedCornerShape(CORNER_SHAPE_SMALL),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = MaterialTheme.colors.secondary,
            focusedIndicatorColor = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
            focusedLabelColor = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = priority.iconResource),
                contentDescription = null,
                tint = priority.color
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    expanded = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = stringResource(id = R.string.priorityDropdown)
                )
            }
        }
    )

    DropdownMenu(
        modifier = Modifier
            .fillMaxWidth(0.93f),
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        }
    ) {
        for (prio in Priority.values()){
            DropdownMenuItem(
                onClick = {
                    onEvent(ToDoTaskEvent.OnPriorityChanged(prio))
                    expanded = false
                }
            ) {
                PriorityItem(priority = prio)
            }
        }
    }
}