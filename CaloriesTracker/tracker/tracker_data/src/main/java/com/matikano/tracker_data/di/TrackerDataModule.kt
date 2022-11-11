package com.matikano.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.matikano.tracker_data.local.TrackerDatabase
import com.matikano.tracker_data.remote.OpenFoodApi
import com.matikano.tracker_data.repository.TrackerRepositoryImpl
import com.matikano.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

    @Provides
    @Singleton
    fun provideOpenFoodApi(
        client: OkHttpClient
    ): OpenFoodApi =
        Retrofit.Builder()
            .baseUrl(OpenFoodApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()

    @Provides
    @Singleton
    fun provideTrackerDatabase(
        app: Application
    ): TrackerDatabase =
        Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            TrackerDatabase.DATABASE_NAME,
        ).build()

    @Provides
    @Singleton
    fun provideTrackerRepository(
        api: OpenFoodApi,
        db: TrackerDatabase
    ): TrackerRepository =
        TrackerRepositoryImpl(
            api = api,
            dao = db.dao
        )
}