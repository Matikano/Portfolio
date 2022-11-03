package com.matikano.onboarding_presentation.height

sealed class HeightEvent {
    object OnNextClick: HeightEvent()
    data class OnHeightChanged(val height: String): HeightEvent()
}
