package com.example.uichallanges.subscription_manager.model

data class SubCategory(
    val name: String,
    val subscriptions: List<Payment> = emptyList()
)
