package com.gmail.matikano9.mobilebankappui.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.ui.graphics.vector.ImageVector
import com.gmail.matikano9.mobilebankappui.R
import kotlin.random.Random

data class Account(
    val title: String,
    val amount: Double = randomAmount(),
    val type: AccountType,
    val number: String = randomNumber()
){
    companion object{
        private fun randomNumber(): String =
            " ${Random.nextInt(10, 100)} " +
                    "${Random.nextInt(10000)} " +
                    "${Random.nextInt(10000)} " +
                    "${Random.nextInt(10000)} " +
                    "${Random.nextInt(10000)} " +
                    "${Random.nextInt(10000)}"

        private fun randomAmount(): Double = Random.nextDouble(1000.0, 99999.99)
    }
}

enum class AccountType(
    @DrawableRes val imageRes: Int,
    @DrawableRes val iconRes: Int,
    val actionTitle: String,
){
    Personal(R.mipmap.account_card_bg_foreground, R.drawable.ic_money, "Transfer"),
    Savings(R.mipmap.savings_bg_foreground, R.drawable.ic_savings, "Deposit"),
    CreditCard(R.mipmap.credit_card_bg_foreground, R.drawable.ic_credit_card, "Pay off")

}
