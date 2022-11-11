package com.matikano.tracker_presentation.compontnts

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.matikano.core.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun LocalDate.parseDateText(): String {
    val today = LocalDate.now()
    return when(this) {
        today.minusDays(1) -> stringResource(id = R.string.yesterday)
        today -> stringResource(id = R.string.today)
        today.plusDays(1) -> stringResource(id = R.string.tomorrow)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(this)
    }
}