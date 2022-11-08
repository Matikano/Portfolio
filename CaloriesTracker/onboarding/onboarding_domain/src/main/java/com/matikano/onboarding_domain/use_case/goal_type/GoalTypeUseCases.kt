package com.matikano.onboarding_domain.use_case.goal_type

import com.matikano.core.domain.use_case.LoadUserInfoUseCase

data class GoalTypeUseCases(
    val saveGoalType: SaveGoalTypeUseCase,
    val loadUserInfo: LoadUserInfoUseCase

)
