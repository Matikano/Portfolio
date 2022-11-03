package com.matikano.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.util.UiEvent
import com.matikano.core.util.UiText
import com.matikano.core.R
import com.matikano.core.navigation.Screens
import com.matikano.onboarding_domain.use_case.height.HeightUseCases
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HeightViewModel @Inject constructor (
    private val useCases: HeightUseCases
): ViewModel() {

    var state by mutableStateOf(HeightState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HeightEvent) {
        when(event){
            is HeightEvent.OnHeightChanged -> {
                if (state.height.length <= 3) {
                    state = state.copy(
                        height = useCases.filterOutDigits(event.height)
                    )
                }
            }
            is HeightEvent.OnNextClick -> viewModelScope.launch {
                val height = state.height.toIntOrNull() ?: kotlin.run {
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            message = UiText.StringResource(R.string.error_height_cant_be_empty)
                        )
                    )
                    return@launch
                }
                useCases.saveHeight(height)
                _uiEvent.send(UiEvent.Navigate(Screens.WEIGHT))
            }
        }
    }
}