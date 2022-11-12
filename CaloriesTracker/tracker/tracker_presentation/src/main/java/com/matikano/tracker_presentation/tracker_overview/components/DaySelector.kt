package com.matikano.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.tracker_presentation.compontnts.parseDateText
import com.matikano.tracker_presentation.tracker_overview.TrackerOverviewEvent
import com.matikano.core.R
import java.time.LocalDate

@Composable
fun DaySelector(
    date: LocalDate,
    onEvent: (TrackerOverviewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = spacing.spaceSmall,
                horizontal = spacing.spaceLarge
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                onEvent(TrackerOverviewEvent.OnPreviousDayClick)
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.previous_day),
                tint = MaterialTheme.colors.onBackground
            )
        }
        Text(
            text = date.parseDateText(),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground
        )
        IconButton(
            onClick = {
                onEvent(TrackerOverviewEvent.OnNextDayClick)
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = stringResource(id = R.string.next_day),
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}