package com.gmail.matikano9.todoapp.di

import com.gmail.matikano9.todoapp.domain.validation.ValidateDate
import com.gmail.matikano9.todoapp.domain.validation.ValidateDescription
import com.gmail.matikano9.todoapp.domain.validation.ValidateTime
import com.gmail.matikano9.todoapp.domain.validation.ValidateTitle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ValidationModule {

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