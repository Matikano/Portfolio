package com.matikano.complimentapp.di.modules

import com.matikano.complimentapp.domain.preferences.Preferences
import com.matikano.complimentapp.domain.use_cases.settings.GetNotificationReminderSettingsUseCase
import com.matikano.complimentapp.domain.use_cases.settings.SaveNotificationReminderSettingsUseCase
import com.matikano.complimentapp.domain.use_cases.settings.SettingsUseCases
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
    fun provideSettingsUseCases(
        preferences: Preferences
    ): SettingsUseCases =
        SettingsUseCases(
            GetNotificationReminderSettingsUseCase(preferences),
            SaveNotificationReminderSettingsUseCase(preferences)
        )

}