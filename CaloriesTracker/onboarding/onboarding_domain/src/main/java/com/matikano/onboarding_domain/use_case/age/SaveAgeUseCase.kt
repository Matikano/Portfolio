package com.matikano.onboarding_domain.use_case.age

import com.matikano.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveAgeUseCase (
    private val preferences: Preferences
) {
    suspend operator fun invoke(age: Int) {
        preferences.saveAge(age)
    }
}