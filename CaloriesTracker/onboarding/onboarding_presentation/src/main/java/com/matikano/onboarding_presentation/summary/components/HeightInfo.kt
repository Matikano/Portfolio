package com.matikano.onboarding_presentation.summary.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matikano.core.R
import com.matikano.core.util.UiText

@Composable
fun HeightInfo(
    modifier: Modifier = Modifier,
    height: Int
) {
    InfoRow(
        modifier = modifier,
        title = UiText.StringResource(R.string.height),
        info = height.toString(),
        unit = UiText.StringResource(R.string.cm)
    )
}