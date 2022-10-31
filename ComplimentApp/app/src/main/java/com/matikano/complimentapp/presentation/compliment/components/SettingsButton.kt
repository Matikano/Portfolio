package com.matikano.complimentapp.presentation.compliment.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.matikano.complimentapp.navigation.Screens
import com.matikano.complimentapp.presentation.util.UiEvent

@Composable
fun SettingsButton(
    onSettingsClicked: (UiEvent.Navigate) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = {
                onSettingsClicked(UiEvent.Navigate(Screens.SETTINGS))
            }
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.Settings,
                tint = Color.White,
                contentDescription = "settings",
            )
        }
    }

}