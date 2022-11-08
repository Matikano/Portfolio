package com.matikano.onboarding_presentation.goal_type

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.navigation.Screens
import com.matikano.core.util.UiEvent
import com.matikano.onboarding_domain.use_case.goal_type.GoalTypeUseCases
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class GoalTypeViewModel @Inject constructor (
    private val useCases: GoalTypeUseCases
): ViewModel() {

    var state by mutableStateOf(GoalTypeState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        state = state.copy(
            goalType = useCases.loadUserInfo().goalType
        )
    }

    fun onEvent(event: GoalTypeEvent) {
        when(event){
            is GoalTypeEvent.OnGoalTypeClickType ->
                state = state.copy(
                    goalType = event.goalType
                )

            is GoalTypeEvent.OnNextClick -> viewModelScope.launch {
                useCases.saveGoalType(state.goalType)
                _uiEvent.send(UiEvent.Navigate(Screens.ACTIVITY))
            }

            is GoalTypeEvent.OnNavigateBackClick -> viewModelScope.launch {
                _uiEvent.send(UiEvent.PopBackStack)
            }
        }
    }
}