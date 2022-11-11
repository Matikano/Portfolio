package com.matikano.tracker_data.mapper

import com.matikano.tracker_data.local.entity.TrackedFoodEntity
import com.matikano.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood =
    TrackedFood(
        name = name,
        imageUrl = imageUrl,
        carbs = carbs,
        protein = protein,
        fat = fat,
        calories = calories,
        date = LocalDate.of(year, month, dayOfMonth),
        amount = amount,
        mealType = type,
        id = id
    )

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity =
    TrackedFoodEntity(
        name = name,
        imageUrl = imageUrl,
        carbs = carbs,
        protein = protein,
        fat = fat,
        calories = calories,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        amount = amount,
        type = mealType,
        id = id
    )