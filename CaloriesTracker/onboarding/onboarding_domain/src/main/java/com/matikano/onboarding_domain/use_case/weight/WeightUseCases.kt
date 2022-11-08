package com.matikano.onboarding_domain.use_case.weight

import com.matikano.core.domain.use_case.LoadUserInfoUseCase
import javax.inject.Inject

data class WeightUseCases (
    val saveWeight: SaveWeightUseCase,
    val loadUserInfo: LoadUserInfoUseCase
)
