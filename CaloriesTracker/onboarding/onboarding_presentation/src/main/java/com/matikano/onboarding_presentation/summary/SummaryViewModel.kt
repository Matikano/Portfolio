package com.matikano.onboarding_presentation.summary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.util.UiEvent
import com.matikano.onboarding_domain.use_case.summary.SummaryUseCases
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SummaryViewModel @Inject constructor (
    private val useCases: SummaryUseCases,
): ViewModel() {

    var state by mutableStateOf(SummaryState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        state = state.copy(
            userInfo = useCases.loadUserInfo()
        )
    }

    fun onEvent(event: SummaryEvent) {
        when(event) {
            is SummaryEvent.OnNavigateBackClick -> viewModelScope.launch {
                _uiEvent.send(UiEvent.PopBackStack)
            }
            is SummaryEvent.OnConfirmClick -> viewModelScope.launch {
                useCases.saveOnBoardingNeeded(false)
            }
        }
    }
}