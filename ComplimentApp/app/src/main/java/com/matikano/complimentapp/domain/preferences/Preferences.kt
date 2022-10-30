package com.matikano.complimentapp.domain.preferences

interface  Preferences {
    suspend fun getReminderHour(): Int
    suspend fun getReminderMinute(): Int
    suspend fun getIntervalInHours(): Long

    suspend fun saveReminderHour(hour: Int)
    suspend fun saveReminderMinute(minute: Int)
    suspend fun saveIntervalInHours(hours: Long)
}