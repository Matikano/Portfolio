package com.matikano.onboarding_presentation.goal

import com.matikano.core.domain.model.GoalType

sealed class GoalEvent {
    object OnNextClick: GoalEvent()
    data class OnGoalTypeClick(val goalType: GoalType): GoalEvent()
}
