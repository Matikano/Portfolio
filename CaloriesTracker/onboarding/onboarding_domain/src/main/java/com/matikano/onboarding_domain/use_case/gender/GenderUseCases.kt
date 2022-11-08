package com.matikano.onboarding_domain.use_case.gender

import com.matikano.core.domain.use_case.LoadUserInfoUseCase
import javax.inject.Inject

data class GenderUseCases (
    val saveGender: SaveGenderUseCase,
    val loadUserInfo: LoadUserInfoUseCase
)