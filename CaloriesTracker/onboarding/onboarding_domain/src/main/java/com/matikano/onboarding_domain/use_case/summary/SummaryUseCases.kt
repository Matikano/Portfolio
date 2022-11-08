package com.matikano.onboarding_domain.use_case.summary

import com.matikano.core.domain.use_case.LoadUserInfoUseCase

data class SummaryUseCases (
    val saveOnBoardingNeeded: SaveOnBoardingNeededUseCase,
    val loadUserInfo: LoadUserInfoUseCase
)
