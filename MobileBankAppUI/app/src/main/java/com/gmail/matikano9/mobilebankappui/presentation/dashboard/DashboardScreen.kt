package com.gmail.matikano9.mobilebankappui.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardAccountCard
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardAdd
import com.gmail.matikano9.mobilebankappui.ui.theme.Surface
import com.gmail.matikano9.mobilebankappui.domain.model.Account
import com.gmail.matikano9.mobilebankappui.domain.model.AccountType
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

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
        val accounts = listOf(
           Account(
                title = "${AccountType.Personal.name} account",
                type = AccountType.Personal,
            ),
            Account(
                title = "${AccountType.Savings.name} account",
                type = AccountType.Savings,
            ),
            Account(
                title = AccountType.CreditCard.name,
                type = AccountType.CreditCard,
            ),
        )

        DashboardAdd(
            title = "Special offer!",
            description = "Recommend account to a friend and earn $200"
        )
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        LazyRow(
            modifier = Modifier,
            content = {

                items(accounts.size) { i ->
                    val account = accounts[i]
                    if(i == 0){
                        Spacer(
                            modifier = Modifier
                                .width(20.dp)
                                .background(color = Color.Transparent)
                        )
                    }
                    DashboardAccountCard(
                        account = account,
                        onHistoryClicked = {},
                        onTransferClicked = {},
                        onMoreClicked = {}
                    )

                }
            }
        )
    }
}