package com.matikano.weatherapp.presentation.ui.util

import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer

object Extensions {

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
}