package com.matikano.onboarding_presentation.age

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.domain.preferences.Preferences
import com.matikano.core.domain.use_case.FilterOutDigitsUseCase
import com.matikano.core.util.UiEvent
import com.matikano.core.util.UiText
import com.matikano.core.R
import com.matikano.core.navigation.Screens
import com.matikano.onboarding_domain.use_case.age.AgeUseCases
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AgeViewModel @Inject constructor (
    private val useCases: AgeUseCases,
): ViewModel() {

    var state by mutableStateOf(AgeState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        state = state.copy(
            age = useCases.loadUserInfo().age.toString()
        )
    }

    fun onEvent(event: AgeEvent) {
        when(event) {
            is AgeEvent.OnAgeChanged -> {
                if(event.age.length <= 2) {
                    state = state.copy(
                        age = useCases.filterOutDigits(event.age)
                    )
                }
            }
            is AgeEvent.OnNextClick -> viewModelScope.launch {
               val age = state.age.toIntOrNull() ?: kotlin.run {
                   _uiEvent.send(
                       UiEvent.ShowSnackBar(
                           UiText.StringResource(R.string.error_age_cant_be_empty)
                       )
                   )
                   return@launch
               }
                useCases.saveAge(age)
                _uiEvent.send(UiEvent.Navigate(Screens.HEIGHT))
            }
            is AgeEvent.OnNavigateBackClick -> viewModelScope.launch {
                _uiEvent.send(UiEvent.PopBackStack)
            }
        }
    }
}