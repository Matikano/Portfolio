package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import com.example.uichallanges.subscription_manager.model.Screen
import com.example.uichallanges.subscription_manager.model.allScreens
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerEvent
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerState
import com.example.uichallanges.subscription_manager.theme.BlueMain
import com.example.uichallanges.subscription_manager.theme.MediumGrey

@Composable
fun TopBarTabs(
    modifier: Modifier = Modifier,
    screens: List<Screen> = allScreens,
    state: SubscriptionManagerState,
    onEvent: (SubscriptionManagerEvent) -> Unit,
    scale: Float = 1f
) {
    val selectedItemIndex = screens.indexOf(state.screen)

    val indicatorOffset by animateDpAsState(
        targetValue = (SCREEN_TAB_WIDTH_SELECTED - SCREEN_TAB_WIDTH_NOT_SELECTED - SCREEN_TAB_HORIZONTAL_PADDING) * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing)
    )

    Box(
        modifier = modifier
            .clip(CircleShape)
            .scale(scale)
            .background(MediumGrey)
            .height(intrinsicSize = IntrinsicSize.Min)
    ) {
        TabIndicator(
            indicatorWidth = SCREEN_TAB_WIDTH_SELECTED,
            indicatorOffset = indicatorOffset,
            indicatorColor = BlueMain
        )
        Row(
            modifier = modifier
                .clip(CircleShape)
        ) {
            screens.forEach { screen ->
                ScreenTab(
                    screen = screen,
                    onEvent = onEvent,
                    isSelected = state.screen == screen
                )
            }
        }
    }
}