package com.gmail.matikano9.todoapp.di

import com.gmail.matikano9.todoapp.domain.repository.ToDoRepository
import com.gmail.matikano9.todoapp.domain.use_case.common.DeleteTask
import com.gmail.matikano9.todoapp.domain.use_case.todo_list.DeleteAllTasks
import com.gmail.matikano9.todoapp.domain.use_case.todo_list.GetAllTasks
import com.gmail.matikano9.todoapp.domain.use_case.todo_list.GetSearchedTasks
import com.gmail.matikano9.todoapp.domain.use_case.todo_list.GetSortedTasks
import com.gmail.matikano9.todoapp.domain.use_case.todo_task.AddTask
import com.gmail.matikano9.todoapp.domain.use_case.todo_task.UpdateTask
import com.gmail.matikano9.todoapp.domain.use_case.validation.ValidateDate
import com.gmail.matikano9.todoapp.domain.use_case.validation.ValidateDescription
import com.gmail.matikano9.todoapp.domain.use_case.validation.ValidateTime
import com.gmail.matikano9.todoapp.domain.use_case.validation.ValidateTitle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideDeleteTask(
        repository: ToDoRepository
    ): DeleteTask = DeleteTask(repository)

    @Module
    @InstallIn(ViewModelComponent::class)
    object ValidationModule{
        @ViewModelScoped
        @Provides
        fun provideValidateTitle(): ValidateTitle = ValidateTitle()

        @ViewModelScoped
        @Provides
        fun provideValidateDescription(): ValidateDescription = ValidateDescription()

        @ViewModelScoped
        @Provides
        fun provideValidateDate(): ValidateDate = ValidateDate()

        @ViewModelScoped
        @Provides
        fun provideValidateTime(): ValidateTime = ValidateTime()
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    object ToDoListModule{

        @ViewModelScoped
        @Provides
        fun provideDeleteAllTasks(
            repository: ToDoRepository
        ): DeleteAllTasks = DeleteAllTasks(repository)

        @ViewModelScoped
        @Provides
        fun provideGetAllTasks(
            repository: ToDoRepository
        ): GetAllTasks = GetAllTasks(repository)

        @ViewModelScoped
        @Provides
        fun provideGetSearchedTasks(
            repository: ToDoRepository
        ): GetSearchedTasks = GetSearchedTasks(repository)

        @ViewModelScoped
        @Provides
        fun provideGetSortedTasks(
            repository: ToDoRepository
        ): GetSortedTasks = GetSortedTasks(repository)

    }

    @Module
    @InstallIn(ViewModelComponent::class)
    object ToDoTaskModule{

        @ViewModelScoped
        @Provides
        fun providesAddTask(
            repository: ToDoRepository
        ): AddTask = AddTask(repository)

        @ViewModelScoped
        @Provides
        fun providesUpdateTask(
            repository: ToDoRepository
        ): UpdateTask = UpdateTask(repository)
    }


}