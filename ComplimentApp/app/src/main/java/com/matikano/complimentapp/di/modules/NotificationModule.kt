package com.matikano.complimentapp.di.modules

import com.matikano.complimentapp.data.notification.ComplimentNotificationServiceImpl
import com.matikano.complimentapp.domain.notification.ComplimentNotificationService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationModule {

    @Binds
    @Singleton
    abstract fun bindNotificationService(
         complimentNotificationServiceImpl: ComplimentNotificationServiceImpl
    ): ComplimentNotificationService
}