package com.matikano.tracker_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matikano.tracker_domain.model.MealType

@Entity
data class TrackedFoodEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val type: MealType,
    val amount: Int,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    val calories: Int
)
