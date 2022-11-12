package com.matikano.onboarding_presentation.summary.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import com.matikano.core.util.UiText
import com.matikano.core_ui.theme.LocalSpacing

@Composable
fun InfoRow(
    modifier: Modifier = Modifier,
    title: UiText,
    info: String,
    unit: UiText? = null,
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.spaceSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = title.asString(context),
                style = MaterialTheme.typography.h3,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = info,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.alignBy(LastBaseline)
            )
            Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
            unit?.let { unit ->
                Text(
                    text = unit.asString(context),
                    modifier = Modifier.alignBy(LastBaseline),
                )
            }
        }
    }
}