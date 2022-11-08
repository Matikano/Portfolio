package com.matikano.onboarding_presentation.height

sealed class HeightEvent {
    object OnNextClick: HeightEvent()
    object OnNavigateBackClick: HeightEvent()
    data class OnHeightChanged(val height: String): HeightEvent()
}
