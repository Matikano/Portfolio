package com.matikano.complimentapp.presentation.settings

data class SettingsState (
    val hour: Int = 0,
    val minute: Int = 0,
    val intervalInHours: Long = 24L
)
