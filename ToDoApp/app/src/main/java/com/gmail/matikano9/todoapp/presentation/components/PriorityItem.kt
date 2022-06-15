package com.gmail.matikano9.todoapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.presentation.ui.theme.SPACE_SMALL

@Composable
fun PriorityItem(
    priority: Priority
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            painter = painterResource(id = priority.iconResource),
            contentDescription = null,
            tint = priority.color
        )
        Spacer(modifier = Modifier.width(SPACE_SMALL))
        Text(text = priority.name.toUpperCase())
    }
}

@Composable
@Preview
fun LowPriorityItemPreview() {
    PriorityItem(priority = Priority.Low)
}

@Composable
@Preview
fun MediumPriorityItemPreview() {
    PriorityItem(priority = Priority.Medium)
}

@Composable
@Preview
fun HighPriorityItemPreview() {
    PriorityItem(priority = Priority.High)
}