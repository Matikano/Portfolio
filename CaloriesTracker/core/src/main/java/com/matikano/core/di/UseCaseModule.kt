package com.matikano.core.di

import com.matikano.core.domain.preferences.Preferences
import com.matikano.core.domain.use_case.FilterOutDigitsUseCase
import com.matikano.core.domain.use_case.LoadUserInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideFilterOutDigitsUseCase(): FilterOutDigitsUseCase = FilterOutDigitsUseCase()

    @Provides
    @ViewModelScoped
    fun provideLoadUserInfoUseCase(
        preferences: Preferences
    ): LoadUserInfoUseCase = LoadUserInfoUseCase(preferences)

}