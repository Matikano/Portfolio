package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uichallanges.subscription_manager.model.Payment
import com.example.uichallanges.subscription_manager.theme.TextWhite
import com.example.uichallanges.subscription_manager.theme.Yellow
import com.example.uichallanges.subscription_manager.theme.unbounded
import com.example.uichallanges.ui.theme.overlappingOffset
import com.example.uichallanges.utils.cutOfZeroDecimals
import com.example.uichallanges.utils.formatToNextPayment

@Composable
fun SubscriptionItem(
    payment: Payment,
    onDetailsClick: () -> Unit,
    isLast: Boolean = false,
) {
    val subscription = payment.subscription
    val textColor = if(subscription.color == Yellow) Color.Black else TextWhite

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
       modifier = Modifier
           .fillMaxWidth()
           .clip(RoundedCornerShape(32.dp))
           .clickable {
               expanded = !expanded
           }
           .background(subscription.color)
           .padding(vertical = 24.dp, horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = subscription.name,
                    color = textColor,
                    fontSize = 18.sp,
                    fontFamily = unbounded
                )
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    text = "$ ${subscription.amount.cutOfZeroDecimals()} / ${subscription.paymentPeriod.name.lowercase()}",
                    color = Color.Black,
                    fontFamily = unbounded,
                    fontSize = 12.sp
                )
            }
            SubLogo(
                iconRes = subscription.icon,
                size = 56.dp
            )
        }
        if(!isLast) Spacer(modifier = Modifier.height(overlappingOffset))
        AnimatedVisibility(
            visible = expanded
        ) {
            Column() {
                if(isLast) Spacer(modifier = Modifier.height(overlappingOffset))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Details",
                            fontFamily = unbounded,
                            color = textColor,
                            fontSize = 14.sp
                        )
                        Text(
                            text = payment.paymentDateTime.formatToNextPayment(textColor),
                            fontSize = 12.sp,
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .size(32.dp)
                            .padding(8.dp),
                        onClick = onDetailsClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
                if(!isLast) Spacer(modifier = Modifier.height(overlappingOffset))
            }
        }
    }
}