package com.gmail.matikano9.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.matikano9.todoapp.domain.model.ToDoTask
import com.gmail.matikano9.todoapp.util.Constants.Database.DATABASE_VERSION

@Database(
    entities = [ToDoTask::class],
    version = DATABASE_VERSION
)
abstract class ToDoDatabase: RoomDatabase() {
    abstract val toDoDao: ToDoDao
}
