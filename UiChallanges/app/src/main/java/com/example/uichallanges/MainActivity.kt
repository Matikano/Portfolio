package com.example.uichallanges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerScreen
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerViewModel
import com.example.uichallanges.ui.theme.UiChallangesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UiChallangesTheme {
                // A surface container using the 'background' color from the theme
                val viewModel = viewModel<SubscriptionManagerViewModel>()
                val state by viewModel.stateFlow.collectAsState()

                SubscriptionManagerScreen(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}
