package com.gmail.matikano9.todoapp.presentation.todo_task.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gmail.matikano9.todoapp.presentation.ui.theme.CORNER_SHAPE_SMALL


@Composable
fun ToDoTaskTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    isError: Boolean,
    onTextChanged: (String) -> Unit,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onTextChanged,
        shape = RoundedCornerShape(CORNER_SHAPE_SMALL),
        label = {
            Text(text = label)
        },
        isError = isError,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = MaterialTheme.colors.secondary,
            focusedIndicatorColor = MaterialTheme.colors.secondary,
            focusedLabelColor = MaterialTheme.colors.secondary,
            ),
        singleLine = singleLine,
        maxLines = maxLines,
        leadingIcon = leadingIcon
    )
}