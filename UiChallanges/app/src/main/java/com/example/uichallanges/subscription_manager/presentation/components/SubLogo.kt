package com.example.uichallanges.subscription_manager.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SubLogo(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    size: Dp = 64.dp,
    iconPadding: Dp = 8.dp,
) {
    Box(
        modifier = modifier
            .size(size = size)
            .clip(CircleShape)
            .background(Color.White)
            .padding(iconPadding),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = "Subscription logo",
        )
    }
}