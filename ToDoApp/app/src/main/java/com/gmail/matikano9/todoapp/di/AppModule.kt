package com.gmail.matikano9.todoapp.di

import android.app.Application
import androidx.room.Room
import com.gmail.matikano9.todoapp.data.database.ToDoDao
import com.gmail.matikano9.todoapp.data.database.ToDoDatabase
import com.gmail.matikano9.todoapp.domain.validation.*
import com.gmail.matikano9.todoapp.util.Constants.Database.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): ToDoDatabase {
        return Room.databaseBuilder(
            app,
            ToDoDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideToDoDao(
        database: ToDoDatabase
    ): ToDoDao = database.toDoDao

}