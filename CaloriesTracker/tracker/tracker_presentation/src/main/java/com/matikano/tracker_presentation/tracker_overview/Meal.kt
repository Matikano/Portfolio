package com.matikano.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import com.matikano.core.util.UiText
import com.matikano.tracker_domain.model.MealType
import com.matikano.core.R
import com.matikano.core.util.extension.capitalizeEnum

data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val calories: Int = 0,
    val expanded: Boolean = false
)

val defaultMeals: List<Meal> =
    MealType.values()
        .map { mealType ->
            Meal(
                name = UiText.DynamicString(mealType.name.capitalizeEnum()),
                mealType = mealType,
                drawableRes = when(mealType) {
                    MealType.BREAKFAST -> R.drawable.ic_breakfast
                    MealType.LUNCH -> R.drawable.ic_lunch
                    MealType.DINNER -> R.drawable.ic_dinner
                    MealType.SNACKS -> R.drawable.ic_snack
                }
            )
        }



