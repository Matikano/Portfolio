package com.matikano.onboarding_domain.use_case.gender

import com.matikano.core.domain.model.Gender
import com.matikano.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveGenderUseCase (
    private val preferences: Preferences
) {
    suspend operator fun invoke(gender: Gender) {
        preferences.saveGender(gender)
    }
}