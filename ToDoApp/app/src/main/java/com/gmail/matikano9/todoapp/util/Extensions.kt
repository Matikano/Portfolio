package com.gmail.matikano9.todoapp.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Extensions {
    fun LocalDateTime.toMillis(zone: ZoneId = ZoneId.systemDefault()) = atZone(zone)?.toInstant()?.toEpochMilli()

    fun LocalDateTime.toDateString()
    = this.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT))

    fun LocalDateTime.toTimeString()
            = this.format(DateTimeFormatter.ofPattern(Constants.TIME_FORMAT))
}