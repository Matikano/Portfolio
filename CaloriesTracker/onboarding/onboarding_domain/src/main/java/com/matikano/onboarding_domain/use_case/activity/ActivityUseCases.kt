package com.matikano.onboarding_domain.use_case.activity

import com.matikano.core.domain.use_case.LoadUserInfoUseCase

data class ActivityUseCases(
    val saveActivity: SaveActivityUseCase,
    val loadUserInfo: LoadUserInfoUseCase
)
