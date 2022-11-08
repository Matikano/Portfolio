package com.matikano.onboarding_presentation.gender

import com.matikano.core.domain.model.Gender

sealed class GenderEvent {
    object OnNextClick: GenderEvent()
    object OnNavigateBackClick: GenderEvent()
    data class OnGenderClicked(val gender: Gender): GenderEvent()
}
