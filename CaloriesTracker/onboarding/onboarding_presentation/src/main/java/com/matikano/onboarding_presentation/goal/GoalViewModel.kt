package com.matikano.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.navigation.Screens
import com.matikano.core.util.UiEvent
import com.matikano.onboarding_domain.use_case.goal.GoalUseCases
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class GoalViewModel @Inject constructor (
    private val useCases: GoalUseCases
): ViewModel() {

    var state by mutableStateOf(GoalState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: GoalEvent) {
        when(event){
            is GoalEvent.OnGoalTypeClick ->
                state = state.copy(
                    goalType = event.goalType
                )

            is GoalEvent.OnNextClick -> viewModelScope.launch {
                useCases.saveGoal(state.goalType)
                _uiEvent.send(UiEvent.Navigate(Screens.ACTIVITY))
            }
        }
    }
}