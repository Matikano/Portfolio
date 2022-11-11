package com.matikano.core.domain.preferences

import com.matikano.core.domain.model.ActivityLevel
import com.matikano.core.domain.model.Gender
import com.matikano.core.domain.model.GoalType
import com.matikano.core.domain.model.UserInfo

interface Preferences {

    fun saveGender(gender: Gender)
    fun saveGoalType(type: GoalType)
    fun saveActivityLevel(level: ActivityLevel)
    fun saveAge(age: Int)
    fun saveWeight(weight: Float)
    fun saveHeight(height: Int)
    fun saveCarbRatio(ratio: Float)
    fun saveProteinRatio(ratio: Float)
    fun saveFatRatio(ratio: Float)
    fun saveOnBoarding(onBoarding: Boolean)

    fun loadUserInfo(): UserInfo
    fun loadOnBoarding(): Boolean

    companion object {
        const val KEY_GENDER = "gender"
        const val KEY_AGE = "age"
        const val KEY_WEIGHT = "weight"
        const val KEY_HEIGHT = "height"
        const val KEY_ACTIVITY_LEVEL = "activityLevel"
        const val KEY_GOAL_TYPE = "goalType"
        const val KEY_CARBS_RATIO = "carbsRatio"
        const val KEY_PROTEIN_RATIO = "proteinRatio"
        const val KEY_FAT_RATIO = "fatRatio"
        const val KEY_ON_BOARDING = "onBoarding"
    }
}