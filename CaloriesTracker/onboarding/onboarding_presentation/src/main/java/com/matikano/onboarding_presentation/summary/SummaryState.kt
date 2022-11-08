package com.matikano.onboarding_presentation.summary

import com.matikano.core.domain.model.UserInfo

data class SummaryState(
    val userInfo: UserInfo = UserInfo()
)
