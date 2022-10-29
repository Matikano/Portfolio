package com.matikano.complimentapp.presentation.settings

sealed class SettingsEvent {
    object OnSaveClick: SettingsEvent()
    object OnDatePickerClick: SettingsEvent()
    object OnNavigateBackClick: SettingsEvent()
    data class OnDatePicked(val hour: Int, val minute: Int): SettingsEvent()
    data class OnIntervalChanged(val intervalInHours: Long): SettingsEvent()
}