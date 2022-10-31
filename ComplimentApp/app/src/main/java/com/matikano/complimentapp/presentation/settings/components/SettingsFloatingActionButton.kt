package com.matikano.complimentapp.presentation.settings.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.matikano.complimentapp.presentation.settings.SettingsEvent

@Composable
fun SettingsFloatingActionButton(
    onEvent: (SettingsEvent) -> Unit
) {
    FloatingActionButton(
        backgroundColor = MaterialTheme.colors.primary,
        onClick = {
            onEvent(SettingsEvent.OnSaveClick)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = "save",
            tint = Color.White
        )
    }
}