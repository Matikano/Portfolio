package com.gmail.matikano9.mobilebankappui.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardAccountCard
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardAdd
import com.gmail.matikano9.mobilebankappui.ui.theme.Surface
import com.gmail.matikano9.mobilebankappui.domain.model.Account
import com.gmail.matikano9.mobilebankappui.domain.model.AccountType
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import kotlin.random.Random

@RootNavGraph(start = true)
@Destination
@Composable
fun DashboardScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(color = Surface)
            .fillMaxSize(),
    ) {
        DashboardAdd(
            title = "Special offer!",
            description = "Recommend account to a friend and earn $200"
        )
        Spacer(modifier = Modifier.height(32.dp))
        LazyRow(
            modifier = Modifier.padding(start = 20.dp),
            content = {
                items(AccountType.values()) { type ->
                    DashboardAccountCard(
                        account = Account(
                            type.name,
                            Random.nextDouble(1000.0, 5000.0),
                            type,
                            "00 1231 1321 5325 0040 2362"
                        ),
                        onHistoryClicked = {},
                        onTransferClicked = {},
                        onMoreClicked = {}
                    )

                }
            }
        )
    }
}