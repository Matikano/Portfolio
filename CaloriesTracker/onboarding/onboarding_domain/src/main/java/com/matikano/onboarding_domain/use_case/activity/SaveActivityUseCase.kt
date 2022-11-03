package com.matikano.onboarding_domain.use_case.activity

import com.matikano.core.domain.model.ActivityLevel
import com.matikano.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveActivityUseCase (
    private val preferences: Preferences
) {
    suspend operator fun invoke(level: ActivityLevel) {
        preferences.saveActivityLevel(level)
    }
}