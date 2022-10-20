package com.matikano.complimentapp

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import com.matikano.complimentapp.data.local.NotificationSettingsDataStore
import com.matikano.complimentapp.data.notification.ComplimentNotificationServiceImpl
import com.matikano.complimentapp.data.receivers.NotificationAlarmReceiver
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration
import javax.inject.Inject

@HiltAndroidApp
class ComplimentApp: Application(){

    @Inject
    lateinit var dataStore: NotificationSettingsDataStore

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        setUpAlarmManager()
    }

    private fun setUpAlarmManager() = CoroutineScope(Dispatchers.IO).launch {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, dataStore.getReminderHour())
            set(Calendar.MINUTE, dataStore.getReminderMinute())
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
            Duration.ofHours(dataStore.getIntervalInHours()).toMillis(),
            pendingIntent
        )
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