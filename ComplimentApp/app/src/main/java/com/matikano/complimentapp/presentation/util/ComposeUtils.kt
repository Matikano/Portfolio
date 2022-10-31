package com.matikano.complimentapp.presentation.util

import androidx.compose.runtime.Composable
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun formatTime(hour: Int, minute: Int): String =
    LocalTime.of(hour, minute).format(DateTimeFormatter.ofPattern("hh:mm"))