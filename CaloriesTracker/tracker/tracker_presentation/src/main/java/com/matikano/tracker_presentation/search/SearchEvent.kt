package com.matikano.tracker_presentation.search

import com.matikano.tracker_domain.model.MealType
import com.matikano.tracker_domain.model.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {
    object OnNavigateBackClick: SearchEvent()
    object OnSearch: SearchEvent()
    data class OnQueryChange(val query: String): SearchEvent()
    data class OnToggleTrackableFood(val food: TrackableFood): SearchEvent()
    data class OnAmountForFoodChange(
        val amount: String,
        val food: TrackableFood,
    ): SearchEvent()
    data class OnTrackFoodClick(
        val food: TrackableFood,
        val mealType: MealType,
        val date: LocalDate
    ) : SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean): SearchEvent()
}
