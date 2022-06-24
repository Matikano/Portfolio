package com.gmail.matikano9.mobilebankappui.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import com.gmail.matikano9.mobilebankappui.ui.theme.BasicGradient

object Extensions {
    fun Double.format(digits: Int) = "%.${digits}f".format(this)

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