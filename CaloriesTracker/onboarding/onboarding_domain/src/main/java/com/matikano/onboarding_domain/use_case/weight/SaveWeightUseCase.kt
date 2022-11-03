package com.matikano.onboarding_domain.use_case.weight

import com.matikano.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveWeightUseCase (
    private val preferences: Preferences
) {
    suspend operator fun invoke(weight: Float) {
        preferences.saveWeight(weight)
    }
}