package com.example.uichallanges.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import com.example.uichallanges.subscription_manager.theme.TextGrey
import com.example.uichallanges.subscription_manager.theme.TextWhite
import com.example.uichallanges.subscription_manager.theme.unbounded
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.US
    ) else it.toString()
}

fun Float.toCurrentMonthAmountString(): AnnotatedString  {
    val text = "$ $this"
    return buildAnnotatedString {
        pushStyle(SpanStyle(color = TextWhite))
        append(text.substringBefore('.'))
        pushStyle(SpanStyle(color = TextGrey))
        append(".${text.substringAfter('.')}")
        toAnnotatedString()
    }
}

fun Float.cutOfZeroDecimals(): String
    = when(toString().substringAfter('.')) {
        "0" -> toInt().toString()
        else -> toString()
    }

fun LocalDateTime.formatToString(pattern: String) =
    DateTimeFormatter.ofPattern(pattern).format(this)

fun LocalDateTime.formatToNextPayment(textColor: Color): AnnotatedString = buildAnnotatedString {
    val style = SpanStyle(
        color = textColor,
        fontFamily = unbounded
    )
    pushStyle(style)
    append("Next payment: ")
    pushStyle(style.copy(fontWeight = FontWeight.Bold))
    append(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(this@formatToNextPayment))
    toAnnotatedString()
}


fun LocalDateTime.formatToHistoryDateString(): String  {
    val datePart = when(this.toLocalDate()) {
            LocalDate.now() -> "Today"
            LocalDate.now().plusDays(-1) -> "Yesterday"
            else -> DateTimeFormatter.ofPattern("dd.MM.yyyy").format(this.toLocalDate())
    }
    val timePart = this.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))

    return "$datePart, at $timePart"
}

