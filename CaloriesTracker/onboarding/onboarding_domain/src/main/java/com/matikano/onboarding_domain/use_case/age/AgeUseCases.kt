package com.matikano.onboarding_domain.use_case.age

import com.matikano.core.domain.use_case.FilterOutDigitsUseCase
import com.matikano.core.domain.use_case.LoadUserInfoUseCase
import javax.inject.Inject

data class AgeUseCases (
    val saveAge: SaveAgeUseCase,
    val filterOutDigits: FilterOutDigitsUseCase,
    val loadUserInfo: LoadUserInfoUseCase
)
