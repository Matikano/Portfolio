package com.matikano.complimentapp.domain.use_cases.settings

data class SettingsUseCases (
    val getNotificationReminderSettings: GetNotificationReminderSettingsUseCase,
    val saveNotificationReminderSettings: SaveNotificationReminderSettingsUseCase
)