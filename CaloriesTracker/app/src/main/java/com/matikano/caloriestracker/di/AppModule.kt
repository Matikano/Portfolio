package com.matikano.caloriestracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.matikano.core.data.DefaultPreferences
import com.matikano.core.domain.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferences(
        sharedPreferences: SharedPreferences
    ): Preferences = DefaultPreferences(sharedPreferences)

    @Provides
    @Singleton
    fun provideSharedPreferences(
        application: Application
    ): SharedPreferences = application.getSharedPreferences("shared_pref", MODE_PRIVATE)
}