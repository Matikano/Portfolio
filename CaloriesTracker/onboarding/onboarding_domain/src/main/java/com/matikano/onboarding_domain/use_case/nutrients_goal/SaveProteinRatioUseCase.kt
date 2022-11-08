package com.matikano.onboarding_domain.use_case.nutrients_goal

import com.matikano.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveProteinRatioUseCase (
    private val preferences: Preferences
) {
    suspend operator fun invoke(ratio: Float) {
        preferences.saveProteinRatio(ratio)
    }
}