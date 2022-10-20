package com.matikano.complimentapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.preferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationSettingsDataStore @Inject constructor(
    @ApplicationContext context: Context
): BaseDataStore(context) {

    override val name: String = DS_NAME

    private val reminderHourKey = preferencesKey<Int>(DS_KEY_REMINDER_HOUR)
    private val reminderMinuteKey = preferencesKey<Int>(DS_KEY_REMINDER_MINUTE)
    private val intervalKey = preferencesKey<Long>(DS_KEY_INTERVAL_IN_HOURS)

    // getters
    suspend fun getReminderHour(): Int = get(reminderHourKey) ?: 8 // returns saved value or default one
    suspend fun getReminderMinute(): Int = get(reminderMinuteKey) ?: 0
    suspend fun getIntervalInHours(): Long = get(intervalKey) ?: 24

    // setters
    suspend fun saveReminderHour(hour: Int) = set(reminderHourKey, hour)
    suspend fun saveReminderMinute(minute: Int) = set(reminderMinuteKey, minute)
    suspend fun saveInterval(hours: Long) = set(intervalKey, hours)


    companion object {
        const val DS_NAME = "notificationSettings"

        const val DS_KEY_REMINDER_HOUR = "reminderHour"
        const val DS_KEY_REMINDER_MINUTE = "reminderMinute"
        const val DS_KEY_INTERVAL_IN_HOURS = "intervalHours"
    }
}


