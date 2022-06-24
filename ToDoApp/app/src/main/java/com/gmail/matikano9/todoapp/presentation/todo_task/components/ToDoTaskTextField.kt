package com.gmail.matikano9.todoapp.presentation.todo_task.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
    enabled: Boolean = true,
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

            disabledLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
            disabledPlaceholderColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
            disabledTextColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
            disabledLeadingIconColor = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
            disabledTrailingIconColor = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
            ),
        singleLine = singleLine,
        enabled = enabled,
        maxLines = maxLines,
        leadingIcon = leadingIcon
    )
}