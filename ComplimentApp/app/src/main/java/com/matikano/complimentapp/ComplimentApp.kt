package com.matikano.complimentapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.matikano.complimentapp.data.notification.ComplimentNotificationServiceImpl
<<<<<<< Updated upstream
import com.matikano.complimentapp.domain.receivers.NotificationAlarmReceiver
=======
import com.matikano.complimentapp.di.preferences.Preferences
import com.matikano.complimentapp.util.extenstions.scheduleNotifications
>>>>>>> Stashed changes
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ComplimentApp: Application(){

    @Inject
    lateinit var dataStore: Preferences

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        scheduleNotifications(dataStore)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            ComplimentNotificationServiceImpl.COMPLIMENT_CHANNEL_ID,
            ComplimentNotificationServiceImpl.CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = ComplimentNotificationServiceImpl.CHANNEL_DESCRIPTION
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}