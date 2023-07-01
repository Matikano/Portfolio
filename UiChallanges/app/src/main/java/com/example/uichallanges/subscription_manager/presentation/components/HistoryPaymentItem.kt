package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uichallanges.subscription_manager.model.Payment
import com.example.uichallanges.subscription_manager.theme.LightGrey
import com.example.uichallanges.subscription_manager.theme.TextWhite
import com.example.uichallanges.subscription_manager.theme.unbounded
import com.example.uichallanges.utils.cutOfZeroDecimals
import com.example.uichallanges.utils.formatToHistoryDateString

@Composable
fun HistoryPaymentItem(
    modifier: Modifier = Modifier,
    payment: Payment
) {
    val subscription = payment.subscription
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(LightGrey)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubLogo(iconRes = subscription.icon, size = 48.dp, iconPadding = 8.dp)
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = subscription.name,
                fontSize = 16.sp,
                color = TextWhite,
                fontFamily = unbounded
            )
            Text(
                text = payment.paymentDateTime.formatToHistoryDateString(),
                fontSize = 10.sp,
                color = TextWhite,
                fontFamily = unbounded,
                fontWeight = FontWeight.Light
            )
        }
        Text(
            text = "- $ ${payment.subscription.amount.cutOfZeroDecimals()}",
            fontFamily = unbounded,
            fontSize = 18.sp,
            color = TextWhite
        )

    }
}