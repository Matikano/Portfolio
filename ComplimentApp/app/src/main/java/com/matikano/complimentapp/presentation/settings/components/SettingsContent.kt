package com.matikano.complimentapp.presentation.settings.components

import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Update
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matikano.complimentapp.presentation.compliment.components.UnitTextField
import com.matikano.complimentapp.presentation.settings.SettingsEvent
import com.matikano.complimentapp.presentation.settings.SettingsState
import com.matikano.complimentapp.presentation.util.formatTime

@Composable
fun SettingsContent(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _: TimePicker, hour: Int, minute: Int ->
            onEvent(SettingsEvent.OnDatePicked(hour, minute))
        }, state.hour.toInt(), state.minute.toInt(), true
    )
    val textStyle = TextStyle(
        color = Color.White,
        fontSize = 40.sp
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    timePickerDialog.show()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .size(64.dp),
                imageVector = Icons.Rounded.Schedule,
                tint = Color.White,
                contentDescription = "reminder time"
            )
            Text(
                text = formatTime(
                    hour = state.hour.toInt(),
                    minute = state.minute.toInt()
                ),
                color = textStyle.color,
                fontSize = textStyle.fontSize
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .size(64.dp),
                tint = Color.White,
                imageVector = Icons.Rounded.Update,
                contentDescription = "reminder time"
            )
            UnitTextField(
                value = state.intervalInHours,
                onValueChange = { interval ->
                    onEvent(SettingsEvent.OnIntervalChanged(interval))
                },
                unit = "h",
                textStyle = textStyle
            )
        }
    }
}