package com.matikano.onboarding_presentation.activity

import com.matikano.core.domain.model.ActivityLevel

data class ActivityState(
    val activityLevel: ActivityLevel = ActivityLevel.MEDIUM
)
