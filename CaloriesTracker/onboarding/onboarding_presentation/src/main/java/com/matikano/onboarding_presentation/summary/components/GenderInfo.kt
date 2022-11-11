package com.matikano.onboarding_presentation.summary.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matikano.core.R
import com.matikano.core.domain.model.Gender
import com.matikano.core.util.UiText
import com.matikano.core.util.extension.capitalizeEnum


@Composable
fun GenderInfo(
    modifier: Modifier = Modifier,
    gender: Gender
) {
    InfoRow(
        modifier = modifier,
        title = UiText.StringResource(R.string.gender),
        info = gender.name.capitalizeEnum(),
    )
}