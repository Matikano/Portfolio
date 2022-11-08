package com.matikano.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.util.UiEvent
import com.matikano.core.util.UiText
import com.matikano.onboarding_domain.use_case.weight.WeightUseCases
import com.matikano.core.R
import com.matikano.core.navigation.Screens
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeightViewModel @Inject constructor (
    private val useCases: WeightUseCases
): ViewModel() {

    var state by mutableStateOf(WeightState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        state = state.copy(
            weight = useCases.loadUserInfo().weight.toString()
        )
    }

    fun onEvent(event: WeightEvent) {
        when(event) {
            is WeightEvent.OnNextClick -> viewModelScope.launch {
                val weight = state.weight.toFloatOrNull() ?: kotlin.run {
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            message = UiText.StringResource(R.string.error_weight_cant_be_empty)
                        )
                    )
                    return@launch
                }
                useCases.saveWeight(weight)
                _uiEvent.send(UiEvent.Navigate(Screens.GOAL))
            }
            is WeightEvent.OnWeightChange -> {
                if(state.weight.length <= 5) {
                    state = state.copy(
                        weight = event.weight
                    )
                }
            }
            is WeightEvent.OnNavigateBack -> viewModelScope.launch {
                _uiEvent.send(UiEvent.PopBackStack)
            }
        }
    }
}