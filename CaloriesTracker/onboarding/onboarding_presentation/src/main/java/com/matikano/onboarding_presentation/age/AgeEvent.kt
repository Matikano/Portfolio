package com.matikano.onboarding_presentation.age

sealed class AgeEvent {
    object OnNextClick: AgeEvent()
    object OnNavigateBackClick: AgeEvent()
    data class OnAgeChanged(val age: String): AgeEvent()
}
