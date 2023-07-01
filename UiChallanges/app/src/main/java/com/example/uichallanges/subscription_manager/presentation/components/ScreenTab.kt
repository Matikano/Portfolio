package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.TabPosition
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.uichallanges.subscription_manager.model.Screen
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerEvent
import com.example.uichallanges.subscription_manager.theme.BlueMain
import com.example.uichallanges.subscription_manager.theme.MediumGrey
import com.example.uichallanges.subscription_manager.theme.TextWhite
import com.example.uichallanges.subscription_manager.theme.unbounded

@Composable
fun ScreenTab(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onEvent: (SubscriptionManagerEvent) -> Unit,
    screen: Screen,
) {
    Row(
        modifier = modifier
            .zIndex(2f)
            .clip(CircleShape)
            .width(if (isSelected) SCREEN_TAB_WIDTH_SELECTED else SCREEN_TAB_WIDTH_NOT_SELECTED)
            .clickable {
                onEvent(SubscriptionManagerEvent.OnTabClicked(screen))
            }
            .padding(vertical = 8.dp, horizontal = SCREEN_TAB_HORIZONTAL_PADDING),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = if(isSelected) screen.selectedIcon else screen.notSelectedIcon,
            contentDescription = null,
            tint = TextWhite
        )
        if(isSelected) {
            Text(
                modifier = Modifier.offset(y = (-3).dp),
                text = screen.label,
                color = TextWhite,
                textAlign = TextAlign.Center,
                fontFamily = unbounded
            )
        }
    }
}

val SCREEN_TAB_WIDTH_SELECTED = 144.dp
val SCREEN_TAB_WIDTH_NOT_SELECTED = 64.dp
val SCREEN_TAB_HORIZONTAL_PADDING = 16.dp