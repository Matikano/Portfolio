package com.matikano.onboarding_domain.use_case.height

import com.matikano.core.domain.use_case.FilterOutDigitsUseCase
import com.matikano.core.domain.use_case.LoadUserInfoUseCase
import javax.inject.Inject

data class HeightUseCases(
    val saveHeight: SaveHeightUseCase,
    val filterOutDigits: FilterOutDigitsUseCase,
    val loadUserInfo: LoadUserInfoUseCase
)
