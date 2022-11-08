package com.matikano.onboarding_domain.use_case.goal_type

import com.matikano.core.domain.model.GoalType
import com.matikano.core.domain.preferences.Preferences

class SaveGoalTypeUseCase (
    private val preferences: Preferences
) {
    suspend operator fun invoke(goalType: GoalType) {
        preferences.saveGoalType(goalType)
    }
}