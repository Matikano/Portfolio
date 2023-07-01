package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uichallanges.subscription_manager.theme.TextWhite

@Composable
fun DashedCircleButton(
    onClick: () -> Unit,
    icon: ImageVector,
    size: Dp = 24.dp,
    contentDescription: String? = null
) {
    Box(
        modifier = Modifier
            .drawBehind {
                drawCircle(
                    color = TextWhite,
                    style = Stroke(
                        width = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
                    )
                )
            }
            .clip(CircleShape)
            .background(Color.Transparent)
            .clickable {
                onClick.invoke()
            }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(size),
            imageVector = icon,
            contentDescription = contentDescription,
            tint = TextWhite
        )
    }
}