package com.matikano.tracker_domain.use_case.tracker_overview

import com.matikano.core.domain.model.*
import com.matikano.core.domain.preferences.Preferences
import com.matikano.tracker_domain.model.MealType
import com.matikano.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrientsUseCase(
    private val preferences: Preferences
) {

    operator fun invoke(trackedFoods: List<TrackedFood>): NutrientsResult {
        val allNutrients = trackedFoods
            .groupBy { it.mealType }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = type
                )
            }
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalProtein = allNutrients.values.sumOf { it.protein }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()

        val caloriesGoal = dailyCaloriesRequirement(userInfo)
        val carbsGoal = (caloriesGoal * userInfo.carbRatio / Nutrients.CARBS.kcalPerGramRatio).roundToInt() // 1g of carbs has 4kcal
        val proteinGoal = (caloriesGoal * userInfo.proteinRatio / Nutrients.PROTEIN.kcalPerGramRatio).roundToInt() // 1g of protein has 4kcal
        val fatGoal = (caloriesGoal * userInfo.fatRatio / Nutrients.FAT.kcalPerGramRatio).roundToInt() // 1g of fat has 9kcal

        return NutrientsResult(
            caloriesGoal = caloriesGoal,
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            totalCalories = totalCalories,
            totalCarbs = totalCarbs,
            totalProtein = totalProtein,
            totalFat = totalFat,
            mealNutrients = allNutrients
        )
    }

    private fun baseMetabolismRate(userInfo: UserInfo): Int =
        when(userInfo.gender) {
            Gender.MALE -> (66.47f + 13.75f * userInfo.weight +
                    5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            Gender.FEMALE -> (665.09f + 9.56f * userInfo.weight +
                    1.84f * userInfo.height - 4.67f * userInfo.age).roundToInt()
        }

    private fun dailyCaloriesRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            ActivityLevel.LOW -> 1.2f
            ActivityLevel.MEDIUM -> 1.3f
            ActivityLevel.HIGH -> 1.4f
        }

        val caloriesExtra = when(userInfo.goalType) {
            GoalType.LOSE_WEIGHT -> -500
            GoalType.KEEP_WEIGHT -> 0
            GoalType.GAIN_WEIGHT -> 500
        }

        return (baseMetabolismRate(userInfo) * activityFactor + caloriesExtra).roundToInt()
    }

    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

    data class NutrientsResult(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )
}