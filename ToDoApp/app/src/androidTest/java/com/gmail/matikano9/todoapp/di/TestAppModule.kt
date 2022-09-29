package com.gmail.matikano9.todoapp.di

import android.content.Context
import androidx.room.Room
import com.gmail.matikano9.todoapp.data.database.ToDoDao
import com.gmail.matikano9.todoapp.data.database.ToDoDatabase
import com.gmail.matikano9.todoapp.util.Constants.Test.TEST_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.junit.runner.manipulation.Ordering
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    @Named(TEST_DATABASE_NAME)
    fun provideInMemoryDb(
        @ApplicationContext context: Context
    ) = Room.inMemoryDatabaseBuilder(context, ToDoDatabase::class.java)
        .allowMainThreadQueries()
        .build()
}