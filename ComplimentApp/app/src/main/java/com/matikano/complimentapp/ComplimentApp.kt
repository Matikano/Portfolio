package com.matikano.complimentapp

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import com.matikano.complimentapp.data.local.NotificationPreferences
import com.matikano.complimentapp.data.notification.ComplimentNotificationService
import com.matikano.complimentapp.data.receivers.NotificationAlarmReceiver
import dagger.hilt.android.HiltAndroidApp
import java.time.Duration
import javax.inject.Inject

@HiltAndroidApp
class ComplimentApp: Application(){

    @Inject
    lateinit var notificationPreferences: NotificationPreferences

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        setUpAlarmManager()
    }

    private fun setUpAlarmManager() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, notificationPreferences.reminderHour)
            set(Calendar.MINUTE, notificationPreferences.reminderMinute)
            set(Calendar.SECOND, 0)
        }

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val thuReq = Calendar.getInstance().timeInMillis + 1 // creating unique request code
        val requestCode = thuReq.toInt()

        if(calendar.timeInMillis < System.currentTimeMillis()){
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            requestCode,
            Intent(applicationContext, NotificationAlarmReceiver::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            },
            PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            Duration.ofHours(notificationPreferences.intervalInHours).toMillis(),
            pendingIntent
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