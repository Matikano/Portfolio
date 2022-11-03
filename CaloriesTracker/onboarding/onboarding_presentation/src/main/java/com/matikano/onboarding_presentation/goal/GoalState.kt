package com.matikano.onboarding_presentation.goal

import com.matikano.core.domain.model.GoalType

data class GoalState(
    val goalType: GoalType = GoalType.KEEP_WEIGHT
)
