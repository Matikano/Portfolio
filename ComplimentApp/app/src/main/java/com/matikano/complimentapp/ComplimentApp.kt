package com.matikano.complimentapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.*
import com.matikano.complimentapp.data.notification.ComplimentNotificationService
import com.matikano.complimentapp.data.workers.ComplimentNotificationWorker
import com.matikano.complimentapp.data.workers.ComplimentNotificationWorker.Companion.REMINDER_HOUR
import com.matikano.complimentapp.data.workers.ComplimentNotificationWorkerFactory
import com.matikano.complimentapp.presentation.ui.util.startOfTheDay
import dagger.hilt.android.HiltAndroidApp
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

@HiltAndroidApp
class ComplimentApp: Application(){

    @Inject
    lateinit var complimentNotificationWorkerFactory: ComplimentNotificationWorkerFactory


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        setUpWorkManager()
    }

    private fun setUpWorkManager() {

        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setWorkerFactory(complimentNotificationWorkerFactory)
                .build()
        )



        val initialDelay =
            if(LocalDateTime.now().hour < REMINDER_HOUR)
                Duration.between(LocalDateTime.now(), LocalDateTime.now().startOfTheDay().plusHours(REMINDER_HOUR))
            else
                Duration.between(LocalDateTime.now(), LocalDateTime.now().startOfTheDay().plusDays(1).plusHours(REMINDER_HOUR))


        val workManager = WorkManager.getInstance(applicationContext)
        val dailyComplimentRequest = PeriodicWorkRequestBuilder<ComplimentNotificationWorker>(
            Duration.ofDays(1)
        )
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setInitialDelay(initialDelay)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "Daily compliments work",
            ExistingPeriodicWorkPolicy.KEEP,
            dailyComplimentRequest
        )
    }

    private fun createNotificationChannel() {

        val channel = NotificationChannel(
            ComplimentNotificationService.COMPLIMENT_CHANNEL_ID,
            ComplimentNotificationService.CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = ComplimentNotificationService.CHANNEL_DESCRIPTION
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }
}