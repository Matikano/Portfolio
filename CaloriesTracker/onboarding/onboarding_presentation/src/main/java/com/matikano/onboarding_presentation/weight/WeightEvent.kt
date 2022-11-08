package com.matikano.onboarding_presentation.weight

sealed class WeightEvent {
    object OnNextClick: WeightEvent()
    object OnNavigateBack: WeightEvent()
    data class OnWeightChange(val weight: String): WeightEvent()
}
