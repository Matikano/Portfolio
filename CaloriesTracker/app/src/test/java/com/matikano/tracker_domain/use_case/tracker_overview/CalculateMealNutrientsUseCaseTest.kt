package com.matikano.tracker_domain.use_case.tracker_overview

import com.google.common.truth.Truth.assertThat
import com.matikano.core.domain.model.UserInfo
import com.matikano.core.domain.preferences.Preferences
import com.matikano.tracker_domain.model.MealType
import com.matikano.tracker_domain.model.TrackedFood
import io.mockk.every
import io.mockk.mockk

import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsUseCaseTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrientsUseCase
    private lateinit var preferences: Preferences
    private lateinit var foods: List<TrackedFood>

    @Before
    fun setUp() {
        preferences = mockk(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo()
        calculateMealNutrients = CalculateMealNutrientsUseCase(preferences)
        foods = (1..NUMBER_OF_FOODS).map {
            TrackedFood(
                name = "Food$it",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                calories = Random.nextInt(2000),
                mealType = MealType.values().random(),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now()
            )
        }
    }

    @Test
    fun `Calories for breakfast properly calculated`(){
        val result = calculateMealNutrients(foods)

        val breakfastCalories = result.mealNutrients.values
            .filter { it.mealType == MealType.BREAKFAST }
            .sumOf { it.calories }

        val expectedCalories = foods
            .filter { it.mealType == MealType.BREAKFAST }
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `Carbs for lunch properly calculated`(){
        val result = calculateMealNutrients(foods)

        val lunchCarbs = result.mealNutrients.values
            .filter { it.mealType == MealType.LUNCH }
            .sumOf { it.carbs }

        val expectedCarbs = foods
            .filter { it.mealType == MealType.LUNCH }
            .sumOf { it.carbs }

        assertThat(lunchCarbs).isEqualTo(expectedCarbs)
    }

    @Test
    fun `Protein for dinner properly calculated`(){
        val result = calculateMealNutrients(foods)

        val dinnerProtein = result.mealNutrients.values
            .filter { it.mealType == MealType.DINNER }
            .sumOf { it.protein }

        val expectedProtein = foods
            .filter { it.mealType == MealType.DINNER }
            .sumOf { it.protein }

        assertThat(dinnerProtein).isEqualTo(expectedProtein)
    }

    @Test
    fun `Fat for snacks properly calculated`(){
        val result = calculateMealNutrients(foods)

        val snacksFat = result.mealNutrients.values
            .filter { it.mealType == MealType.SNACKS }
            .sumOf { it.fat }

        val expectedFat = foods
            .filter { it.mealType == MealType.SNACKS }
            .sumOf { it.fat }

        assertThat(snacksFat).isEqualTo(expectedFat)
    }

    companion object {
        const val NUMBER_OF_FOODS = 30
    }
}