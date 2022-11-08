package com.matikano.onboarding_presentation.summary.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matikano.core.R
import com.matikano.core.domain.model.ActivityLevel
import com.matikano.core.domain.model.Gender
import com.matikano.core.util.UiText
import java.util.*

@Composable
fun ActivityLevelInfo(
    modifier: Modifier = Modifier,
    level: ActivityLevel
) {
    InfoRow(
        modifier = modifier,
        title = UiText.StringResource(R.string.activity_level),
        info = level.name.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    )
}