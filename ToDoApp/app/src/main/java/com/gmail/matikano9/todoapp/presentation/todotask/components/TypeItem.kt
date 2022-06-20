package com.gmail.matikano9.todoapp.presentation.todotask.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.TaskType
import com.gmail.matikano9.todoapp.presentation.ui.theme.SPACE_SMALL
import java.util.*

@Composable
fun TypeItem(
    type: TaskType
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = type.icon,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(SPACE_SMALL))
        Text(text = type.name.capitalize(Locale.ROOT))
    }
}