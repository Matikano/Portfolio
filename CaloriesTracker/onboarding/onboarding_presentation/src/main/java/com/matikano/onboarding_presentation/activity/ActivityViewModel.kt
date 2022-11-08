package com.matikano.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.navigation.Screens
import com.matikano.core.util.UiEvent
import com.matikano.onboarding_domain.use_case.activity.ActivityUseCases
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ActivityViewModel @Inject constructor (
    private val useCases: ActivityUseCases
): ViewModel() {

    var state by mutableStateOf(ActivityState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        state = state.copy(
            activityLevel = useCases.loadUserInfo().activityLevel
        )
    }

    fun onEvent(event: ActivityEvent) {
        when(event) {
            is ActivityEvent.OnActivityClick ->
                state = state.copy(
                    activityLevel = event.level
                )

            is ActivityEvent.OnNextClick -> viewModelScope.launch {
                useCases.saveActivity(state.activityLevel)
                _uiEvent.send(UiEvent.Navigate(Screens.NUTRIENT_GOAL))
            }
            is ActivityEvent.OnNavigateBackClick -> viewModelScope.launch {
                _uiEvent.send(UiEvent.PopBackStack)
            }
        }
    }
}