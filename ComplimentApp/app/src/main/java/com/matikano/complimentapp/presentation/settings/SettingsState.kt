package com.matikano.complimentapp.presentation.settings

data class SettingsState (
    val hour: String = "8",
    val minute: String = "0",
    val intervalInHours: String = "24"
)
