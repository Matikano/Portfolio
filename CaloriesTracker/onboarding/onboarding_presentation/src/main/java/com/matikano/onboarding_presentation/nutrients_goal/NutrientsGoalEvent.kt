package com.matikano.onboarding_presentation.nutrients_goal

sealed class NutrientsGoalEvent {
    object OnNextClick: NutrientsGoalEvent()
    object OnNavigateBackClicked: NutrientsGoalEvent()
    data class OnCarbsChanged(val carbs: String): NutrientsGoalEvent()
    data class OnProteinChanged(val protein: String): NutrientsGoalEvent()
    data class OnFatChanged(val fat: String): NutrientsGoalEvent()
}