package com.matikano.complimentapp.di

import androidx.work.CoroutineWorker
import com.matikano.complimentapp.data.workers.ChildWorkerFactory
import com.matikano.complimentapp.data.workers.ComplimentNotificationWorker
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out CoroutineWorker>)

@Module
@InstallIn(SingletonComponent::class)
abstract class ComplimentNotificationWorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(ComplimentNotificationWorker::class)
    internal abstract fun bindComplimentNotificationWorker(worker: ComplimentNotificationWorker.Factory): ChildWorkerFactory
}