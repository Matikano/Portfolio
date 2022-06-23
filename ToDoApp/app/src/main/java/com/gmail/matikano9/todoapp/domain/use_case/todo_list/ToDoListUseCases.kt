package com.gmail.matikano9.todoapp.domain.use_case.todo_list

import com.gmail.matikano9.todoapp.domain.use_case.common.DeleteTask
import javax.inject.Inject

data class ToDoListUseCases @Inject constructor(
    val deleteTask: DeleteTask,
    val deleteAllTasks: DeleteAllTasks,
    val getAllTasks: GetAllTasks,
    val getSearchedTasks: GetSearchedTasks,
    val getSortedTasks: GetSortedTasks
)
