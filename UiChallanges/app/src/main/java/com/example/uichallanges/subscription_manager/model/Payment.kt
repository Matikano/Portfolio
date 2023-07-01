package com.example.uichallanges.subscription_manager.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import com.example.uichallanges.subscription_manager.theme.unbounded
import com.example.uichallanges.utils.formatToString
import java.time.LocalDateTime

data class Payment(
    val subscription: Subscription,
    val paymentDateTime: LocalDateTime
)

fun Payment.toAnnotatedString(datePattern: String): AnnotatedString =
    buildAnnotatedString {
        val spanStyle = SpanStyle(
            fontFamily = unbounded
        )
        pushStyle(spanStyle)
        append("Upcoming payment: ")
        pushStyle(
            spanStyle.copy(
                fontWeight = FontWeight.Bold
            )
        )
        append(paymentDateTime.formatToString(datePattern))
        toAnnotatedString()
    }

val paymentList = subscriptions.mapIndexed { index, subscription ->
    subscription.toPayment(
        LocalDateTime.now()
            .plusDays(-index.toLong() + 1)
            .plusMinutes((-30L..30L).random())
            .plusHours((-12L..12L).random())
    )
}


