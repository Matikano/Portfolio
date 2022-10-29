package com.matikano.complimentapp.domain.use_cases.settings

import com.matikano.complimentapp.domain.notification.preferences.Preferences
import javax.inject.Inject

class GetNotificationReminderSettingsUseCase @Inject constructor(
   private val preferences: Preferences
) {
    suspend operator fun invoke(): NotificationReminderSettings =
        NotificationReminderSettings(
            hour = preferences.getReminderHour(),
            minute = preferences.getReminderMinute(),
            intervalInHours = preferences.getIntervalInHours()
        )
}

