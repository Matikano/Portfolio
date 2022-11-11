package com.matikano.onboarding_presentation.nutrients_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.util.UiEvent
import com.matikano.core.util.ValidationResult
import com.matikano.core_ui.navigation.Screen
import com.matikano.onboarding_domain.use_case.nutrients_goal.NutrientsGoalUseCases
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class NutrientsGoalViewModel @Inject constructor (
    private val useCases: NutrientsGoalUseCases
): ViewModel() {

    var state by mutableStateOf(NutrientsGoalState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val userInfo = useCases.loadUserInfo()
        state = state.copy(
            carbs = (userInfo.carbRatio * 100).toInt().toString(),
            protein = (userInfo.proteinRatio * 100).toInt().toString(),
            fat = (userInfo.fatRatio * 100).toInt().toString()
        )
    }

    fun onEvent(event: NutrientsGoalEvent) {
        when(event) {
            is NutrientsGoalEvent.OnCarbsChanged ->
                state = state.copy(
                    carbs = useCases.filterOutDigits(event.carbs)
                )

            is NutrientsGoalEvent.OnFatChanged ->
                state = state.copy(
                    fat = useCases.filterOutDigits(event.fat)
                )

            is NutrientsGoalEvent.OnNextClick -> viewModelScope.launch {
                val result = useCases.validateNutrients(
                    carbsString = state.carbs,
                    proteinString = state.protein,
                    fatString = state.fat
                )

                when(result) {
                    is ValidationResult.Error -> _uiEvent.send(
                        UiEvent.ShowSnackBar(result.message)
                    )

                    is ValidationResult.Success -> {
                        useCases.saveNutrients(
                            state.carbs,
                            state.protein,
                            state.fat,
                        )
                        _uiEvent.send(UiEvent.Navigate(Screen.Summary.navigationRoute))
                    }
                }
            }

            is NutrientsGoalEvent.OnProteinChanged ->
                state = state.copy(
                    protein = useCases.filterOutDigits(event.protein)
                )

            is NutrientsGoalEvent.OnNavigateBackClicked -> viewModelScope.launch {
                _uiEvent.send(UiEvent.PopBackStack)
            }
        }
    }
}