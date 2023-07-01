package com.example.uichallanges.subscription_manager.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uichallanges.subscription_manager.model.Screen
import com.example.uichallanges.subscription_manager.presentation.components.GeneralContent
import com.example.uichallanges.subscription_manager.presentation.components.MySubsContent
import com.example.uichallanges.subscription_manager.presentation.components.SubscriptionManagerTopAppBar
import com.example.uichallanges.ui.theme.UiChallangesTheme

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SubscriptionManagerScreen(
    state: SubscriptionManagerState,
    onEvent: (SubscriptionManagerEvent) -> Unit
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            SubscriptionManagerTopAppBar(
                state = state,
                onEvent = onEvent
            )
        },
    ) {
        AnimatedContent(
            targetState = state.screen,
            modifier = Modifier
                .fillMaxSize(),
            content = { screen ->
                when(screen) {
                    Screen.General -> GeneralContent(state = state, onEvent = onEvent)
                    Screen.MySubs -> MySubsContent(state = state, onEvent = onEvent)
                }
            },
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = {
                        if(state.screen == Screen.General) -it else it
                    }
                ) with slideOutHorizontally(
                    targetOffsetX = {
                        if(state.screen == Screen.General) it else -it
                    }
                )
            }
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun SubscriptionManagerScreenPreview() {
    val viewModel = viewModel<SubscriptionManagerViewModel>()
    val state by viewModel.stateFlow.collectAsState()
    UiChallangesTheme {
        SubscriptionManagerScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}