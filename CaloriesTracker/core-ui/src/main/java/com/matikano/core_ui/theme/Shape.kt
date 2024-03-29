package com.matikano.core_ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.dp

val small = RoundedCornerShape(5.dp)
val medium = RoundedCornerShape(8.dp)
val large = RoundedCornerShape(16.dp)
val extraLarge = RoundedCornerShape(32.dp)
val circular = RoundedCornerShape(200.dp)

val rounderCorner: CornerRadius = CornerRadius(100f)

val Shapes = Shapes(
    small = small,
    medium = medium,
    large = large
)
