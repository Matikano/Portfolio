package com.matikano.complimentapp.domain.use_cases.settings

import com.matikano.complimentapp.presentation.settings.SettingsState

data class NotificationReminderSettings(
    val hour: Int,
    val minute: Int,
    val intervalInHours: Long
)

fun NotificationReminderSettings.toState(): SettingsState =
    SettingsState(
        hour = hour.toString(),
        minute = minute.toString(),
        intervalInHours = intervalInHours.toString()
    )

fun SettingsState.toNotificationReminderSettings(): NotificationReminderSettings =
    NotificationReminderSettings(
        hour = hour.toInt(),
        minute = minute.toInt(),
        intervalInHours = intervalInHours.toLong()
    )