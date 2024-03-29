package com.matikano.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.util.UiEvent
import com.matikano.core_ui.navigation.Screen
import com.matikano.onboarding_domain.use_case.gender.GenderUseCases
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class GenderViewModel @Inject constructor (
    private val useCases: GenderUseCases
): ViewModel() {

    var state by mutableStateOf(GenderState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        state = state.copy(
            gender = useCases.loadUserInfo().gender
        )
    }

    fun onEvent(event: GenderEvent){
       when(event) {
           is GenderEvent.OnGenderClicked ->
               state = state.copy(
                   gender = event.gender
               )
           is GenderEvent.OnNextClick -> viewModelScope.launch {
               useCases.saveGender(state.gender)
               _uiEvent.send(UiEvent.Navigate(Screen.Age.navigationRoute))
           }
           is GenderEvent.OnNavigateBackClick -> viewModelScope.launch {
               _uiEvent.send(UiEvent.PopBackStack)
           }
       }
    }
}

