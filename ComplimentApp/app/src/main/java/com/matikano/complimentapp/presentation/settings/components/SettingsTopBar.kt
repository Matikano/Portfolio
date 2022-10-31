package com.matikano.complimentapp.presentation.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.matikano.complimentapp.presentation.settings.SettingsEvent
import com.matikano.complimentapp.presentation.settings.SettingsState
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun SettingsTopBar(
    state: SettingsState,
    showSnackBar: (String) -> Unit,
    onEvent: (SettingsEvent) -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "navigateBack",
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        showSnackBar(
                            "Next notification is scheduled for " +
                                    "${LocalTime.of(state.hour.toInt(), state.minute.toInt()).format(
                                            DateTimeFormatter.ofPattern("hh:mm"))} " +
                                    "repeating every ${state.intervalInHours} hours"
                        )
                        onEvent(SettingsEvent.OnNavigateBackClick)
                    }
            )
        },
        title = {
            Text(
                text = "Settings",
                color = Color.White
            )
        },
    )
}