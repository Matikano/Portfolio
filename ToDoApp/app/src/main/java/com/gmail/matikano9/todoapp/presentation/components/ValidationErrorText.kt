package com.gmail.matikano9.todoapp.presentation.todo_task.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gmail.matikano9.todoapp.presentation.ui.theme.PADDING_VERY_SMALL

@Composable
fun ValidationErrorText(
    modifier: Modifier = Modifier,
    errorText: String
) {
    Text(
        modifier = modifier
            .padding(end = PADDING_VERY_SMALL),
        text = errorText,
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.End
    )
}