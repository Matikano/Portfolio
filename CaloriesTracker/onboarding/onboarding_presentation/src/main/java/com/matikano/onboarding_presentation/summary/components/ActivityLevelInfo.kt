package com.matikano.onboarding_presentation.summary.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matikano.core.R
import com.matikano.core.domain.model.ActivityLevel
import com.matikano.core.util.UiText
import com.matikano.core.util.extension.capitalizeEnum

@Composable
fun ActivityLevelInfo(
    modifier: Modifier = Modifier,
    level: ActivityLevel
) {
    InfoRow(
        modifier = modifier,
        title = UiText.StringResource(R.string.activity_level),
        info = level.name.capitalizeEnum()
    )
}