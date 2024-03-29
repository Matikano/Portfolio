package com.matikano.core.data

import android.content.SharedPreferences
import com.matikano.core.domain.model.ActivityLevel
import com.matikano.core.domain.model.Gender
import com.matikano.core.domain.model.GoalType
import com.matikano.core.domain.model.UserInfo
import com.matikano.core.domain.preferences.Preferences
import javax.inject.Inject

class DefaultPreferences @Inject constructor(
    private val sharedPref: SharedPreferences
): Preferences {

    override fun saveGender(gender: Gender) {
        sharedPref.edit()
            .putString(Preferences.KEY_GENDER, gender.name)
            .apply()
    }

    override fun saveGoalType(type: GoalType) {
        sharedPref.edit()
            .putString(Preferences.KEY_GOAL_TYPE, type.name)
            .apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPref.edit()
            .putString(Preferences.KEY_ACTIVITY_LEVEL, level.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit()
            .putInt(Preferences.KEY_AGE, age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_WEIGHT, weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit()
            .putInt(Preferences.KEY_HEIGHT, height)
            .apply()
    }
    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_CARBS_RATIO, ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_PROTEIN_RATIO, ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_FAT_RATIO, ratio)
            .apply()
    }

    override fun saveOnBoarding(onBoarding: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.KEY_ON_BOARDING, onBoarding)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPref.getInt(Preferences.KEY_AGE, 20)
        val height = sharedPref.getInt(Preferences.KEY_HEIGHT, 170)
        val weight = sharedPref.getFloat(Preferences.KEY_WEIGHT, 80f)
        val genderString = sharedPref.getString(Preferences.KEY_GENDER, null)
        val activityLevelString = sharedPref.getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val goalType = sharedPref.getString(Preferences.KEY_GOAL_TYPE, null)
        val carbRatio = sharedPref.getFloat(Preferences.KEY_CARBS_RATIO, 0.4f)
        val proteinRatio = sharedPref.getFloat(Preferences.KEY_PROTEIN_RATIO, 0.3f)
        val fatRatio = sharedPref.getFloat(Preferences.KEY_FAT_RATIO, 0.3f)

        return UserInfo(
            gender = Gender.valueOf(genderString ?: Gender.MALE.name),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.valueOf(activityLevelString ?: ActivityLevel.MEDIUM.name),
            goalType = GoalType.valueOf(goalType ?: GoalType.KEEP_WEIGHT.name),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }

    override fun loadOnBoarding(): Boolean =
        sharedPref.getBoolean(Preferences.KEY_ON_BOARDING, true)

}