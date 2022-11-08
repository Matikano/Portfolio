package com.matikano.onboarding_presentation.goal_type

import com.matikano.core.domain.model.GoalType

data class GoalTypeState(
    val goalType: GoalType = GoalType.KEEP_WEIGHT
)
