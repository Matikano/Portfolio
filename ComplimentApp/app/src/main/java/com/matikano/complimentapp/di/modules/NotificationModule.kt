package com.matikano.complimentapp.di.modules

import com.matikano.complimentapp.data.notification.ComplimentNotificationService
import com.matikano.complimentapp.domain.notification.NotificationService
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
         complimentNotificationService: ComplimentNotificationService
    ): NotificationService
}