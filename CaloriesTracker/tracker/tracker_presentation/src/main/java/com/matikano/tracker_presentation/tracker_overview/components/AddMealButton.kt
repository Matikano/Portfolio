package com.matikano.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.matikano.caloriestracker.ui.theme.circular
import com.matikano.core_ui.LocalSpacing
import com.matikano.core.R
import com.matikano.core_ui.defaultBorderStroke
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
        shape = circular
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