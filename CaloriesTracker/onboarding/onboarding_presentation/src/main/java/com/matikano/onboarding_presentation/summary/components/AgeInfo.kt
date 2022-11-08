package com.matikano.onboarding_presentation.summary.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matikano.core.util.UiText
import com.matikano.core.R

@Composable
fun AgeInfo(
    modifier: Modifier = Modifier,
    age: Int
) {
    InfoRow(
        modifier = modifier,
        title = UiText.StringResource(R.string.age),
        info = age.toString(),
        unit = UiText.StringResource(R.string.years)
    )
}