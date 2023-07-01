package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerState
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerEvent
import com.example.uichallanges.subscription_manager.theme.DarkGrey
import com.example.uichallanges.ui.theme.UiChallangesTheme
import kotlin.math.absoluteValue

@Composable
fun SubscriptionManagerTopAppBar(
    state: SubscriptionManagerState,
    modifier: Modifier = Modifier,
    onEvent: (SubscriptionManagerEvent) -> Unit
) {
    var screenLaunched by remember {
        mutableStateOf(false)
    }

    val animatedScale by animateFloatAsState(
        targetValue = if(screenLaunched) 1f else 0f,
        animationSpec = tween(500)
    )

    LaunchedEffect(key1 = true) {
        screenLaunched = true
    }

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = DarkGrey,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleButton(
                icon = Icons.Default.Menu,
                onClick = {
                    onEvent(SubscriptionManagerEvent.OnMenuClicked)
                },
                scale = animatedScale
            )
            TopBarTabs(
                modifier = Modifier.clipToBounds(),
                state = state,
                onEvent = onEvent,
                scale = animatedScale,

            )
            CircleButton(
                icon = Icons.Outlined.Notifications,
                onClick = {
                    onEvent(SubscriptionManagerEvent.OnNotificationsClicked)
                },
                scale = animatedScale
            )
        }
    }
}

@Preview
@Composable
fun SubscriptionManagerTopAppBarPreview() {
    UiChallangesTheme {
        SubscriptionManagerTopAppBar(state = SubscriptionManagerState(), onEvent = {})
    }
}