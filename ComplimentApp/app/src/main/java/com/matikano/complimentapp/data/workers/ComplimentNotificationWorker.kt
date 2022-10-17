package com.matikano.complimentapp.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.matikano.complimentapp.data.mappers.toCompliment
import com.matikano.complimentapp.data.remote.ComplimentApi
import com.matikano.complimentapp.domain.notification.NotificationService
import com.matikano.complimentapp.domain.repository.ComplimentRepository
import com.matikano.complimentapp.domain.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ComplimentNotificationWorker @Inject constructor(
    private val repository: ComplimentRepository,
    private val notificationService: NotificationService,
    @ApplicationContext context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {



    override suspend fun doWork(): Result {

        Log.d("COMPLIMENT", "called doWork()")
        return when(val response = repository.getCompliment()) {
            is Resource.Success -> {
                val compliment = response.data!!
                notificationService.showNotification(compliment.content)
                Result.success()
            }
            is Resource.Error -> {
                Result.failure()
            }
        }
    }

    class Factory @Inject constructor(
        val repository: ComplimentRepository,
        val notificationService: NotificationService
    ): ChildWorkerFactory {
        override fun create(context: Context, params: WorkerParameters): CoroutineWorker {
            return ComplimentNotificationWorker(
                repository = repository,
                notificationService = notificationService,
                context = context,
                workerParams = params
            )
        }
    }

    companion object {
        const val REMINDER_HOUR = 8L
    }

}