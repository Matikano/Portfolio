package com.gmail.matikano9.mobilebankappui.presentation.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.PhoneInTalk
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.matikano9.mobilebankappui.R
import com.gmail.matikano9.mobilebankappui.ui.theme.*

@Composable
fun DashboardTopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT)
            .background(
                brush = BasicGradient,
                alpha = 0.95f
            ),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {},
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.my_products),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = AlegreyaSans,
                fontWeight = FontWeight.SemiBold,
                )
        },
        actions = {
            CallAction()
            MessagesAction()
        }
    )
}

@Composable
fun MessagesAction() {
    IconButton(
        onClick = {

        }
    ) {
       Icon(
           modifier = Modifier.size(ACTION_ICON_SIZE),
           imageVector = Icons.Outlined.Chat,
           contentDescription = stringResource(id = R.string.action_message),
           tint = Color.White,
       )
    }
}

@Composable
fun CallAction() {
    IconButton(
        onClick = { 

        }
    ) {
       Icon(
           modifier = Modifier.size(ACTION_ICON_SIZE),
           imageVector = Icons.Outlined.PhoneInTalk,
           contentDescription = stringResource(id = R.string.action_call),
           tint = Color.White,
       )
    }
}