package com.matikano.complimentapp.presentation.ui.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import java.time.LocalDateTime

fun Pair<Color, Color>.toGradient(): Brush =
    Brush.linearGradient(this.toList())

fun Modifier.setGradient(
        gradient: Brush,
        toggle: Boolean = true
    ): Modifier =
        this
            .graphicsLayer(alpha = 0.99f)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    if(toggle) drawRect(
                        gradient,
                        blendMode = BlendMode.SrcAtop
                    )
                }
            }

fun LocalDateTime.startOfTheDay(): LocalDateTime =
    this.withHour(0).withMinute(0).withSecond(0).withNano(0)