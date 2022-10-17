package com.matikano.complimentapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class ComplimentNotificationWorkerFactory @Inject constructor(
    val workerFactories: Map<Class<out CoroutineWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>
): WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val worker = workerFactories[ComplimentNotificationWorker::class.java]
        return worker?.get()?.create(appContext, workerParameters)
    }
}