package com.gmail.matikano9.todoapp.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Extensions {
    fun LocalDateTime.toMillis(zone: ZoneId = ZoneId.systemDefault())
            = atZone(zone)?.toInstant()?.toEpochMilli()

    fun LocalDateTime.toDateString()
            = this.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT))

    fun LocalDate.toDateString()
            = this.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT))

    fun LocalTime.toTimeString()
            = this.format(DateTimeFormatter.ofPattern(Constants.TIME_FORMAT))

    fun LocalDateTime.toTimeString()
            = this.format(DateTimeFormatter.ofPattern(Constants.TIME_FORMAT))

    inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier =
        composed {
            clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
        }
}