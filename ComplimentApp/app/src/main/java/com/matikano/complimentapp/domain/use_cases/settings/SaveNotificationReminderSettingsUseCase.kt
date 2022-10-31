package com.matikano.complimentapp.domain.use_cases.settings

import com.matikano.complimentapp.domain.preferences.Preferences
import javax.inject.Inject

class SaveNotificationReminderSettingsUseCase @Inject constructor(
    private val preferences: Preferences
) {
    suspend operator fun invoke(settings: NotificationReminderSettings) {
        preferences.saveReminderHour(settings.hour)
        preferences.saveReminderMinute(settings.minute)
        preferences.saveIntervalInHours(settings.intervalInHours)
    }
}