package com.matikano.onboarding_domain.use_case.height

import com.matikano.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveHeightUseCase (
    private val preferences: Preferences
) {
    suspend operator fun invoke(height: Int) {
        preferences.saveHeight(height)
    }
}