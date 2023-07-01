package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uichallanges.subscription_manager.model.paymentList
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerEvent
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerState
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerViewModel
import com.example.uichallanges.subscription_manager.theme.DarkGrey

@Composable
fun GeneralContent(
    state: SubscriptionManagerState,
    onEvent: (SubscriptionManagerEvent) -> Unit
) {

    var screenLaunched by remember {
        mutableStateOf(false)
    }

    val offset by animateDpAsState(
        targetValue = if(screenLaunched) 0.dp else 64.dp,
        animationSpec = tween(500)
    )

    LaunchedEffect(key1 = true) {
        screenLaunched = true
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrey)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            AmountInfo(
                amount = state.amount,
                modifier = Modifier.offset(y = offset)
            )
        }
        state.upcomingPayment?.let {payment ->
            item {
                UpcomingPayment(
                    payment = payment,
                    onEvent = onEvent,
                    modifier = Modifier
                        .offset(y = offset)
                )
            }
        }
        item {
            PaymentHistory(
                payments = state.paymentHistory,
                modifier = Modifier.offset(y = offset)
            )
        }
        item { 
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}