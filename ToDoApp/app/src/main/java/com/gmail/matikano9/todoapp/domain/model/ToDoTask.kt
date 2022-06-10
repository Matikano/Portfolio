package com.gmail.matikano9.todoapp.domain.model

import android.os.Parcelable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.domain.model.ToDoTask.Companion.TABLE_NAME
import com.gmail.matikano9.todoapp.presentation.ui.theme.*
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity(tableName = TABLE_NAME)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    val type: TaskType,
    val dueTimeInMillis: Long
): Parcelable {
    companion object{
        const val TABLE_NAME = "todo_task_tab"
    }
}

enum class TaskType(val icon: ImageVector){
     Meeting(Icons.Filled.Person),
     Call( Icons.Filled.Call),
     Email( Icons.Filled.Email),
     Other( Icons.Filled.DateRange),
}

enum class Priority(
    val color: Color,
    val iconResource: Int = R.drawable.ic_priority
){
    Low(priorityLow),
    Medium(priorityMedium),
    High(priorityHigh),
    None(onSurface)
}
