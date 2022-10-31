package com.matikano.complimentapp.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.complimentapp.domain.use_cases.settings.SettingsUseCases
import com.matikano.complimentapp.domain.use_cases.settings.toNotificationReminderSettings
import com.matikano.complimentapp.domain.use_cases.settings.toState
import com.matikano.complimentapp.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor (
    private val useCases: SettingsUseCases
): ViewModel() {


    var state by mutableStateOf(SettingsState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            state = useCases.getNotificationReminderSettings().toState()
        }
    }

    fun onEvent(event: SettingsEvent) {
        when(event){
            is SettingsEvent.OnIntervalChanged ->
                state = state.copy(
                    intervalInHours = event.intervalInHours.toString()
                )

            is SettingsEvent.OnDatePicked ->
                state = state.copy(
                    hour = event.hour.toString(),
                    minute = event.minute.toString()
                )

            is SettingsEvent.OnSaveClick -> viewModelScope.launch {
                useCases.saveNotificationReminderSettings(state.toNotificationReminderSettings())
                _uiEvent.send(UiEvent.PopBackStack)
            }

            is SettingsEvent.OnNavigateBackClick -> viewModelScope.launch {
                _uiEvent.send(UiEvent.PopBackStack)
            }
        }
    }
}
