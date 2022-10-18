package com.matikano.complimentapp.di.modules

import android.content.Context
import com.matikano.complimentapp.data.local.NotificationPreferences
import com.matikano.complimentapp.data.remote.ComplimentApi
import com.matikano.complimentapp.util.Constants.API.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideComplimentApi(): ComplimentApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ComplimentApi::class.java)


    @Provides
    @Singleton
    fun provideNotificationPreferences(
        @ApplicationContext context: Context
    ): NotificationPreferences = NotificationPreferences(context)


}