package com.matikano.onboarding_domain.use_case.goal

import com.matikano.core.domain.model.GoalType
import com.matikano.core.domain.preferences.Preferences

class SaveGoalUseCase (
    private val preferences: Preferences
) {
    suspend operator fun invoke(goalType: GoalType) {
        preferences.saveGoalType(goalType)
    }
}