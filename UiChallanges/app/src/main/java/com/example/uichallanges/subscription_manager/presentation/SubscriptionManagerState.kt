package com.example.uichallanges.subscription_manager.presentation

import com.example.uichallanges.subscription_manager.model.*
import java.time.LocalDateTime

data class SubscriptionManagerState(
    val screen: Screen = Screen.General,
    val amount: Float = 31.45f,
    val payments: List<Payment> = paymentList,
    val categories: List<SubCategory> = listOf(
        SubCategory(
            name = "All subs",
            subscriptions = payments
        ),
        SubCategory(
            name = "Entertainment",
            subscriptions = payments.subList(1, 3)
        )
    ),
    val selectedCategory: SubCategory = categories.first(),
) {
    val upcomingPayment: Payment? =
        payments
            .filter { it.paymentDateTime > LocalDateTime.now() }
            .minByOrNull { it.paymentDateTime }

    val paymentHistory: List<Payment> =
        payments.filter { it.paymentDateTime < LocalDateTime.now() }

    val currentSubscriptions: List<Payment> = selectedCategory.subscriptions
}


