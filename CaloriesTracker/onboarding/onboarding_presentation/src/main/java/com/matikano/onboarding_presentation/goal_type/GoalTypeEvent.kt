package com.matikano.onboarding_presentation.goal_type

import com.matikano.core.domain.model.GoalType

sealed class GoalTypeEvent {
    object OnNextClick: GoalTypeEvent()
    object OnNavigateBackClick: GoalTypeEvent()
    data class OnGoalTypeClickType(val goalType: GoalType): GoalTypeEvent()
}
