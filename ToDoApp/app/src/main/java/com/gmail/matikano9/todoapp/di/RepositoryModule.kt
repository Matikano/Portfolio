package com.gmail.matikano9.todoapp.di

import com.gmail.matikano9.todoapp.data.repository.ToDoRepositoryImpl
import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindToDoRepository(
        toDoRepositoryImpl: ToDoRepositoryImpl
    ) : ToDoRepository
}