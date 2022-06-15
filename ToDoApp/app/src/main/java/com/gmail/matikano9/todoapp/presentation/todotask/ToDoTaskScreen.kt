package com.gmail.matikano9.todoapp.presentation.todotask

import android.util.Log
import androidx.compose.runtime.Composable
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ToDoTaskScreen(
    toDoTask: ToDoTask? = null,
) {
    Log.d("TASK: ", toDoTask?.title ?: "null")
}