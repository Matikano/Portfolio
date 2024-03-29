package com.matikano.onboarding_presentation.activity

import com.matikano.core.domain.model.ActivityLevel

sealed class ActivityEvent {
    object OnNextClick: ActivityEvent()
    object OnNavigateBackClick: ActivityEvent()
    data class OnActivityClick(val level: ActivityLevel): ActivityEvent()
}
