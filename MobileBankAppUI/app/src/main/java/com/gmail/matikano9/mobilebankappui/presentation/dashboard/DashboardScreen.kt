package com.gmail.matikano9.mobilebankappui.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardAccountCard
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardAdd
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardTopAppBar
import com.gmail.matikano9.mobilebankappui.ui.theme.Surface
import com.gmail.matikano9.mobilebankappui.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.NavGraph
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
        DashboardAdd(
            title = "Special offer!",
            description = "Recommend account to a friend and earn $200"
        )
        Spacer(modifier = Modifier.height(32.dp))
        LazyRow(
            modifier = Modifier.padding(start = 20.dp),
            content = {
                items(1) {
                    DashboardAccountCard(
                        icon = Icons.Outlined.MonetizationOn,
                        title = "Personal account",
                        amount = 3000.50f,
                        imageRes = R.mipmap.account_card_bg_foreground,
                        accountNumber = "00 8978 2132 0912 0010 1234",
                        onHistoryClicked = { /*TODO*/ },
                        onArrowClicked = { /*TODO*/ }) {
                    }
                    DashboardAccountCard(
                        icon = Icons.Outlined.Savings,
                        title = "Savings account",
                        amount = 2450.50f,
                        imageRes = R.mipmap.account_card_bg_foreground,
                        accountNumber = "00 1231 4109 0212 0010 1214",
                        onHistoryClicked = { /*TODO*/ },
                        onArrowClicked = { /*TODO*/ }) {
                    }

                    DashboardAccountCard(
                        icon = Icons.Outlined.CreditCard,
                        title = "Credit card",
                        amount = 4500.50f,
                        imageRes = R.mipmap.account_card_bg_foreground,
                        accountNumber = "00 8978 2132 0912 0010 1234",
                        onHistoryClicked = { /*TODO*/ },
                        onArrowClicked = { /*TODO*/ }) {
                    }
                }
            }
        )
    }
}