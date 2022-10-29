package com.matikano.complimentapp.presentation.ui.util

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Pair<Color, Color>.toGradient(): Brush =
    Brush.linearGradient(this.toList())