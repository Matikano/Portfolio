package com.matikano.onboarding_presentation.age

sealed class AgeEvent {
    object OnNextClick: AgeEvent()
    data class OnAgeChanged(val age: String): AgeEvent()
}
