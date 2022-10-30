package com.matikano.complimentapp.util.extenstions

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import com.matikano.complimentapp.domain.preferences.Preferences
import com.matikano.complimentapp.receivers.NotificationAlarmReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration

fun Context.scheduleNotifications(
    preferences: Preferences
) = CoroutineScope(Dispatchers.IO).launch {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, preferences.getReminderHour())
            set(Calendar.MINUTE, preferences.getReminderMinute())
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
            Duration.ofHours(preferences.getIntervalInHours()).toMillis(),
            pendingIntent
        )
}