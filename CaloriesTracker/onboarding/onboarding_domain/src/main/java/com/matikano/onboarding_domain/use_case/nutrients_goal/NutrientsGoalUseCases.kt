package com.matikano.onboarding_domain.use_case.nutrients_goal

import com.matikano.core.domain.use_case.FilterOutDigitsUseCase
import com.matikano.core.domain.use_case.LoadUserInfoUseCase

data class NutrientsGoalUseCases(
    val saveNutrients: SaveNutrientsUseCase,
    val validateNutrients: ValidateNutrientsUseCase,
    val filterOutDigits: FilterOutDigitsUseCase,
    val loadUserInfo: LoadUserInfoUseCase
)
