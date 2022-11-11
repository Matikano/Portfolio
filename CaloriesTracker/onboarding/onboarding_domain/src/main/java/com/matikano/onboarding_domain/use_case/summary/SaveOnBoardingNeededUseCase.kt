package com.matikano.onboarding_domain.use_case.summary

import com.matikano.core.domain.preferences.Preferences

class SaveOnBoardingNeededUseCase(
    private val preferences: Preferences
) {
    suspend operator fun invoke(onBoarding: Boolean) {
        preferences.saveOnBoarding(onBoarding)
    }
}