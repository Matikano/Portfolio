package com.example.uichallanges.subscription_manager.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.uichallanges.R
import com.example.uichallanges.subscription_manager.theme.*
import java.time.LocalDateTime

data class Subscription(
    @DrawableRes val icon: Int,
    val monthlyAmount: Float,
    val name: String,
    val color: Color,
    val textColor: Color = TextWhite,
    val paymentPeriod: PaymentPeriod = PaymentPeriod.MONTH
) {
    val amount = when(paymentPeriod) {
        PaymentPeriod.MONTH -> monthlyAmount
        PaymentPeriod.YEAR -> monthlyAmount * 12
    }
}

fun Subscription.toPayment(dateTime: LocalDateTime) = Payment(
    subscription = this,
    paymentDateTime = dateTime
)

val subscriptions = listOf(
    Subscription(
        icon = R.drawable.ic_spotify,
        monthlyAmount = 8f,
        name = "Spotify",
        color = Green,
    ),
    Subscription(
        icon = R.drawable.ic_figma,
        monthlyAmount = 12f,
        name = "Figma",
        color = Yellow,
        textColor = DarkGrey
    ),
    Subscription(
        icon = R.drawable.ic_hbo,
        monthlyAmount = 9.99f,
        name = "HBO Max",
        color = Pink,
    ),
    Subscription(
        icon = R.drawable.ic_youtube,
        monthlyAmount = 8.97f,
        name = "YouTube",
        color = Orange,
    ),
    Subscription(
        icon = R.drawable.ic_wow,
        monthlyAmount = 11.99f,
        name = "World of Warcraft",
        color = PurpleLight,
        paymentPeriod = PaymentPeriod.YEAR
    ),
    Subscription(
        icon = R.drawable.ic_netflix,
        monthlyAmount = 6.56f,
        name = "NETFLIX",
        color = PurpleDark,
    )
)
