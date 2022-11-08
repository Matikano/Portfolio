package com.matikano.core.domain.use_case

import com.matikano.core.domain.model.UserInfo
import com.matikano.core.domain.preferences.Preferences
import javax.inject.Inject

class LoadUserInfoUseCase (
    private val preferences: Preferences
) {
    operator fun invoke(): UserInfo = preferences.loadUserInfo()
}