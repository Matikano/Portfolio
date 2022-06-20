package com.gmail.matikano9.todoapp.presentation.todo_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.domain.model.Priority
import com.gmail.matikano9.todoapp.domain.model.TaskType
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.presentation.ui.theme.*
import com.gmail.matikano9.todoapp.util.Extensions.toDateString
import com.gmail.matikano9.todoapp.util.Extensions.toMillis
import com.gmail.matikano9.todoapp.util.Extensions.toTimeString
import java.time.LocalDateTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ToDoListItem(
    toDoTask: ToDoTask,
    onItemClicked: (ToDoTask) -> Unit
) {

    val dueDateTime = toDoTask.dueLocalDateTime()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = PADDING_VERY_SMALL,
                top = PADDING_SMALL,
                start = PADDING_SMALL,
                end = PADDING_SMALL
            ),

        onClick = { onItemClicked(toDoTask) },
        elevation = 8.dp,
        shape = Shapes.medium,
        backgroundColor = toDoTask.priority.color,
        contentColor = MaterialTheme.colors.onSurface,
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(PADDING_MEDIUM),

            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                    .weight(6.5f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = toDoTask.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    color = MaterialTheme.colors.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(SPACE_MEDIUM))
                Text(
                    text = toDoTask.description,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(modifier = Modifier
                    .weight(3.5f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween

            ) {

                TypeLabel(type = toDoTask.type)
                Spacer(Modifier.height(SPACE_SMALL))
                DateLabel(dateTime = dueDateTime)
                Spacer(Modifier.height(SPACE_SMALL))
                TimeLabel(dateTime = dueDateTime)
            }
        }
    }
}
@Composable
fun DateLabel(
    dateTime: LocalDateTime?
){
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically

    ){
        Text(
            text = dateTime?.toDateString() ?: "",
            fontSize = MaterialTheme.typography.body2.fontSize
        )
        Spacer(Modifier.width(SPACE_SMALL))
        Icon(
            modifier = Modifier
                .size(LABEL_ICON_SIZE),
            imageVector = Icons.Default.CalendarToday,
            contentDescription = stringResource(id = R.string.typeIcon),

        )
    }
}

@Composable
fun TimeLabel(
    dateTime: LocalDateTime?
){
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically

    ){
        Text(
            text = dateTime?.toTimeString() ?: "",
            fontSize = MaterialTheme.typography.body2.fontSize
        )
        Spacer(Modifier.width(SPACE_SMALL))
        Icon(
            modifier = Modifier
                .size(LABEL_ICON_SIZE),
            imageVector = Icons.Default.Timer,
            contentDescription = stringResource(id = R.string.typeIcon),
        )
    }
}

@Composable
fun TypeLabel(
    type: TaskType,
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = type.name,
            fontSize = MaterialTheme.typography.body2.fontSize
        )
        Spacer(Modifier.width(SPACE_SMALL))
        Icon(
            modifier = Modifier
                .size(LABEL_ICON_SIZE),
            imageVector = type.icon,
            contentDescription = stringResource(id = R.string.typeIcon)
        )
    }
}

@Preview
@Composable
fun ToDoListItemPreview(){
    val toDoTask = ToDoTask(
        title = "SuperDuperLongTitleThatEllipsize",
        description = "Description with\nnew lines that probably will ellipsize",
        priority = Priority.Low,
        dueTimeInMillis = LocalDateTime.now().toMillis()!!,
        type = TaskType.Email
    )

    ToDoListItem(toDoTask = toDoTask, onItemClicked = {})

}