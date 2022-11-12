package com.matikano.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.matikano.core_ui.theme.circular
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.core.R
import com.matikano.core_ui.theme.defaultBorderStroke
import com.matikano.tracker_presentation.tracker_overview.Meal
import com.matikano.tracker_presentation.tracker_overview.TrackerOverviewEvent

@Composable
fun AddMealButton(
    text: String,
    meal: Meal,
    onEvent: (TrackerOverviewEvent) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary
) {
    val spacing = LocalSpacing.current

    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.spaceMedium),
        onClick = {
            onEvent(TrackerOverviewEvent.OnAddFoodClick(meal))
        },
        border = BorderStroke(
            width = defaultBorderStroke,
            color = color
        ),
        shape = circular,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.background
        )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add),
            tint = color
        )
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = color
        )
    }
}