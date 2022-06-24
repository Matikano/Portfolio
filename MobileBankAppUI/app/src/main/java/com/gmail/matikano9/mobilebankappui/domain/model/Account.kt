package com.gmail.matikano9.mobilebankappui.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.ui.graphics.vector.ImageVector
import com.gmail.matikano9.mobilebankappui.R

data class Account(
    val title: String,
    val amount: Double,
    val type: AccountType,
    val number: String
)

enum class AccountType(
    @DrawableRes val imageRes: Int,
    val icon: ImageVector
){
    Personal(R.mipmap.account_card_bg_foreground, Icons.Outlined.MonetizationOn),
    Savings(R.mipmap.savings_bg_foreground, Icons.Outlined.Savings),
    CreditCard(R.mipmap.credit_card_bg_foreground, Icons.Outlined.CreditCard)

}
