package com.matikano.core.domain.model

data class UserInfo(
    val gender: Gender = Gender.MALE,
    val age: Int = 20,
    val weight: Float = 80f,
    val height: Int = 175,
    val activityLevel: ActivityLevel = ActivityLevel.MEDIUM,
    val goalType: GoalType = GoalType.KEEP_WEIGHT,
    val carbRatio: Float = 0.4f,
    val proteinRatio: Float = 0.3f,
    val fatRatio: Float = 0.3f
)
