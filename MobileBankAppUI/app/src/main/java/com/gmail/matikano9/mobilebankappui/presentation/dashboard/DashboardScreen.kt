package com.gmail.matikano9.mobilebankappui.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardAccountCard
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardAdd
import com.gmail.matikano9.mobilebankappui.ui.theme.Surface
import com.gmail.matikano9.mobilebankappui.domain.model.Account
import com.gmail.matikano9.mobilebankappui.domain.model.AccountType
import com.gmail.matikano9.mobilebankappui.domain.model.Transaction
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardChipButton
import com.gmail.matikano9.mobilebankappui.presentation.dashboard.components.DashboardRecentEvents
import com.gmail.matikano9.mobilebankappui.ui.theme.APP_BAR_HEIGHT
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import java.time.LocalDateTime

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

val chips = listOf(
    "BLIK code",
    "BLIK phone transfer",
    "Phone transfer",
    "Buyout insurance",
    "Additional services",
    "Edit bookmarks..."
)

val transactions = listOf(
    Transaction(
        accounts[0],
        "Żabka - card payment",
        -16.08,
        LocalDateTime.now()
    ),
    Transaction(
        accounts[0],
        "Kate - payback for groceries",
        25.0,
        LocalDateTime.now()
    ),
    Transaction(
        accounts[1],
        "Weekly savings",
        5.0,
        LocalDateTime.now().minusDays(1)
    )
)

@RootNavGraph(start = true)
@Destination
@Composable
fun DashboardScreen(
    navController: NavController
) {

    val scrollableState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(color = Surface)
            .padding(bottom = APP_BAR_HEIGHT)
            .fillMaxSize(),
    ) {

        var addOn by remember {
            mutableStateOf(true)
        }

        DashboardAdd(
            title = "Special offer!",
            description = "Recommend account to a friend and earn $200",
            show = addOn,
            onClick = {
                addOn = false
            }
        )
        Column (
            modifier = Modifier
                .background(color = Surface)
                .verticalScroll(scrollableState)
                .fillMaxSize(),
                )
        {
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
            Spacer(
                modifier = Modifier.height(32.dp)
            )
            LazyRow(
                modifier = Modifier,
                content = {
                    items(chips.size) { i ->
                        val title = chips[i]
                        if(i == 0){
                            Spacer(
                                modifier = Modifier
                                    .width(20.dp)
                                    .background(color = Color.Transparent)
                            )
                        }
                        DashboardChipButton(
                            title = title,
                            onClick = {}
                        )
                        if(i == chips.size - 1) {
                            Spacer(
                                modifier = Modifier
                                    .width(8.dp)
                                    .background(color = Color.Transparent)
                            )
                        }
                    }
                }
            )

            DashboardRecentEvents(transactions = transactions)
        }


    }
}