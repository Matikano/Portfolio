package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uichallanges.subscription_manager.model.Payment
import com.example.uichallanges.subscription_manager.model.toAnnotatedString
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerEvent
import com.example.uichallanges.subscription_manager.theme.TextWhite
import com.example.uichallanges.subscription_manager.theme.unbounded
import com.example.uichallanges.utils.cutOfZeroDecimals

@Composable
fun UpcomingPayment(
    modifier: Modifier = Modifier,
    payment: Payment,
    onEvent: (SubscriptionManagerEvent) -> Unit
) {
    val subscription = payment.subscription

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 4.dp
                )
            )
            .background(subscription.color)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SubLogo(iconRes = subscription.icon)
            Text(
                text = "$ ${subscription.amount.cutOfZeroDecimals()}",
                fontFamily = unbounded,
                fontSize = 18.sp,
                color = subscription.textColor
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = subscription.name,
                    color = subscription.textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = unbounded
                )
                Text(
                    text = payment.toAnnotatedString("dd.MM"),
                    color = subscription.textColor,
                    fontSize = 12.sp
                )
            }
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .size(32.dp)
                    .padding(8.dp),
                onClick = {
                    onEvent(SubscriptionManagerEvent.OnUpcomingPaymentClick(payment))
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    }
}