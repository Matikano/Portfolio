package com.matikano.onboarding_presentation.summary.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matikano.core.R
import com.matikano.core.domain.model.GoalType
import com.matikano.core.util.UiText
import com.matikano.core.util.extension.capitalizeEnum


@Composable
fun GoalTypeInfo(
    modifier: Modifier = Modifier,
    goalType: GoalType
) {
    InfoRow(
        modifier = modifier,
        title = UiText.StringResource(R.string.goal_type),
        info = goalType.name.substringBefore('_').capitalizeEnum()
    )
}