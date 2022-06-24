package com.gmail.matikano9.mobilebankappui.domain.model

import java.time.LocalDateTime

data class Transaction(
    val account: Account,
    val title: String,
    val amount: Double,
    val dateTime: LocalDateTime
)
