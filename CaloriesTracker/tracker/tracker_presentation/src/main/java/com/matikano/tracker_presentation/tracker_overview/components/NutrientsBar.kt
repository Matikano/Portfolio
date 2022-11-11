package com.matikano.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.matikano.caloriestracker.ui.theme.circular
import com.matikano.caloriestracker.ui.theme.rounderCorner
import com.matikano.core.domain.model.Nutrients
import com.matikano.core_ui.*

@Composable
fun NutrientsBar(
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    caloriesGoal: Int,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colors.background
    val exceededColor = MaterialTheme.colors.error
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val carbWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = protein) {
        proteinWidthRatio.animateTo(
            targetValue = ((protein * Nutrients.PROTEIN.kcalPerGramRatio) / caloriesGoal),
            animationSpec = basicBarAnimation()
        )
    }
    LaunchedEffect(key1 = carbs) {
        carbWidthRatio.animateTo(
            targetValue = ((carbs * Nutrients.CARBS.kcalPerGramRatio) / caloriesGoal),
            animationSpec = basicBarAnimation()
        )
    }
    LaunchedEffect(key1 = fat) {
        fatWidthRatio.animateTo(
            targetValue = ((fat * Nutrients.FAT.kcalPerGramRatio) / caloriesGoal),
            animationSpec = basicBarAnimation()
        )
    }
    Canvas(modifier = modifier.clip(circular)) {
        if(calories <= caloriesGoal){
            val proteinWidth = proteinWidthRatio.value * size.width
            val carbsWidth = carbWidthRatio.value * size.width
            val fatWidth = fatWidthRatio.value * size.width

            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = rounderCorner
            )
            drawRoundRect(
                color = FatColor,
                size = Size(
                    width = proteinWidth + carbsWidth  + fatWidth,
                    height = size.height
                ),
                cornerRadius = rounderCorner
            )
            drawRoundRect(
                color = CarbsColor,
                size = Size(
                    width = proteinWidth + carbsWidth,
                    height = size.height
                ),
                cornerRadius = rounderCorner
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(
                    width = proteinWidth ,
                    height = size.height
                ),
                cornerRadius = rounderCorner
            )
        } else {
            drawRoundRect(
                color = exceededColor,
                size = size,
                cornerRadius = rounderCorner
            )
        }
    }
}