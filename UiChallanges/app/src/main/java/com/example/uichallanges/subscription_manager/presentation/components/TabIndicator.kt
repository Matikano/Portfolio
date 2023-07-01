package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.example.uichallanges.subscription_manager.theme.BlueMain


@Composable
fun TabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color = BlueMain,
    shape: Shape = CircleShape
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(indicatorWidth)
            .offset(x = indicatorOffset)
            .clip(shape = shape)
            .background(color = indicatorColor),
    )
}