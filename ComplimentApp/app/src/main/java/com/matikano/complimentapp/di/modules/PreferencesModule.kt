package com.matikano.complimentapp.di.modules

import com.matikano.complimentapp.data.preferences.NotificationSettingsDataStore
import com.matikano.complimentapp.di.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun bindNotificationDataStorePreferences(
        notificationSettingsDataStore: NotificationSettingsDataStore
    ): Preferences
}