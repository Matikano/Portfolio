package com.matikano.complimentapp.di

import com.matikano.complimentapp.data.repository.ComplimentRepositoryImpl
import com.matikano.complimentapp.domain.repository.ComplimentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindComplimentRepository(
        complimentRepositoryImpl: ComplimentRepositoryImpl
    ): ComplimentRepository
}