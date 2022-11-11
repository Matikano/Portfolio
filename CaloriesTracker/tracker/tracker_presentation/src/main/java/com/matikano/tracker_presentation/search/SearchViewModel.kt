package com.matikano.tracker_presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.core.util.UiEvent
import com.matikano.core.util.UiText
import com.matikano.tracker_domain.use_case.search.SearchUseCases
import com.matikano.core.R
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor (
    private val useCases: SearchUseCases
): ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when(event){
            is SearchEvent.OnAmountForFoodChange ->
                state = state.copy(
                    trackableFoods = state.trackableFoods.map { foodUiState ->
                        if(foodUiState.food == event.food){
                            foodUiState.copy(
                                amount = useCases.filterOutDigits(event.amount)
                            )
                        } else foodUiState
                    }
                )

            is SearchEvent.OnNavigateBackClick -> viewModelScope.launch {
                _uiEvent.send(UiEvent.PopBackStack)
            }

            is SearchEvent.OnQueryChange ->
                state = state.copy(
                    query = event.query
                )

            is SearchEvent.OnSearch -> viewModelScope.launch {
                state = state.copy(
                    isSearching = true,
                    trackableFoods = emptyList()
                )
                useCases
                    .searchFood(state.query)
                    .onSuccess { foods ->
                        state = state.copy(
                            isSearching = false,
                            trackableFoods = foods.map { food -> food.toUiState() }
                        )
                    }
                    .onFailure {
                        state = state.copy(
                            isSearching = false,
                        )
                        _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_something_went_wrong)))
                    }
            }

            is SearchEvent.OnSearchFocusChange ->
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )

            is SearchEvent.OnToggleTrackableFood ->
                state = state.copy(
                    trackableFoods = state.trackableFoods.map { foodUiState ->
                        if(foodUiState.food == event.food){
                            foodUiState.copy(
                                isExpanded = !foodUiState.isExpanded
                            )
                        } else foodUiState
                    }
                )

            is SearchEvent.OnTrackFoodClick -> viewModelScope.launch {
                val uiState = state.trackableFoods.find { foodUiState -> foodUiState.food == event.food }
                useCases.trackFood(
                    food = uiState?.food ?: return@launch,
                    amount = uiState.amount.toIntOrNull() ?: return@launch,
                    mealType = event.mealType,
                    date = event.date
                )
                _uiEvent.send(UiEvent.PopBackStack)
            }
        }
    }
}