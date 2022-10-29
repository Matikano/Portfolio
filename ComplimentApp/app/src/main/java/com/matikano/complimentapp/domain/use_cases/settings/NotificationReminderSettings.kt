package com.matikano.complimentapp.domain.use_cases.settings

import com.matikano.complimentapp.presentation.settings.SettingsState

data class NotificationReminderSettings(
    val hour: Int,
    val minute: Int,
    val intervalInHours: Long
)

fun NotificationReminderSettings.toState(): SettingsState =
    SettingsState(
        hour = hour,
        minute = minute,
        intervalInHours = intervalInHours
    )

fun SettingsState.toNotificationReminderSettings(): NotificationReminderSettings =
    NotificationReminderSettings(
        hour = hour,
        minute = minute,
        intervalInHours = intervalInHours
    )