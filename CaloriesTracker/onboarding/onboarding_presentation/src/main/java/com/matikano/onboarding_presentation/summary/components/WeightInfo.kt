package com.matikano.onboarding_presentation.summary.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matikano.core.R
import com.matikano.core.util.UiText

@Composable
fun WeightInfo(
    modifier: Modifier = Modifier,
    weight: Float
) {
    InfoRow(
        modifier = modifier,
        title = UiText.StringResource(R.string.weight),
        info = weight.toString(),
        unit = UiText.StringResource(R.string.kg)
    )
}