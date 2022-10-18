package com.matikano.complimentapp.data.local

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(
            SP_KEY,
            Context.MODE_PRIVATE
        )
    }

    val reminderHour: Int = sharedPreferences.getInt(SP_KEY_REMINDER_HOUR, 8)
    val reminderMinute: Int = sharedPreferences.getInt(SP_KEY_REMINDER_MINUTE, 0)
    val intervalInHours: Long = sharedPreferences.getLong(SP_KEY_INTERVAL_IN_HOURS, 24) //Default 1 day

    fun setReminderHour(
        hour: Int
    ) = sharedPreferences.edit {
        putInt(SP_KEY_REMINDER_HOUR, hour)
    }

    fun setReminderMinute(
        minute: Int
    ) = sharedPreferences.edit {
        putInt(SP_KEY_REMINDER_MINUTE, minute)
    }

    fun setIntervalInHours(
        hours: Long
    ) = sharedPreferences.edit {
        putLong(SP_KEY_INTERVAL_IN_HOURS, hours)
    }

    companion object {
        const val SP_KEY = "notificationAlarmPrefs"
        const val SP_KEY_REMINDER_HOUR = "reminderHour"
        const val SP_KEY_REMINDER_MINUTE = "reminderMinute"
        const val SP_KEY_INTERVAL_IN_HOURS = "intervalHours"
    }
}