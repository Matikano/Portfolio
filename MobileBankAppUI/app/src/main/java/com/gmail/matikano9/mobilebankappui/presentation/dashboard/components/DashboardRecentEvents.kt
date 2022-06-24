package com.gmail.matikano9.mobilebankappui.presentation.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.matikano9.mobilebankappui.R
import com.gmail.matikano9.mobilebankappui.domain.model.Transaction
import com.gmail.matikano9.mobilebankappui.ui.theme.AlegreyaSans
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun DashboardRecentEvents(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>
) {

    val todayDate = LocalDate.now()

    val splitTransactions = mapOf<String, MutableList<Transaction>>(
        "Today" to mutableListOf(),
        "Yesterday" to mutableListOf(),
        "2 days ago" to mutableListOf(),
        "3 days ago" to mutableListOf(),
        "4 days ago" to mutableListOf(),
        "5 days ago" to mutableListOf(),
        "6 days ago" to mutableListOf(),
        "week ago" to mutableListOf(),
    )

    transactions.forEach { transaction ->
        val date = transaction.dateTime.toLocalDate()
        val key = when(ChronoUnit.DAYS.between(date, todayDate)){
            0L -> "Today"
            1L -> "Yesterday"
            2L -> "2 days ago"
            3L -> "3 days ago"
            4L -> "4 days ago"
            5L -> "5 days ago"
            6L -> "6 days ago"
            else -> "week ago"
        }

        splitTransactions[key]?.add(transaction)
    }

    Column(
        modifier = modifier
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.recent_events_title),
            fontFamily = AlegreyaSans,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        splitTransactions.forEach { mapEntry ->
            val label = mapEntry.key
            val transactions = mapEntry.value

            if(transactions.isNotEmpty()){
                Text(
                    text = label,
                    fontFamily = AlegreyaSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(){
                    transactions.forEach{ transaction ->
                        DashboardTransactionEvent(transaction = transaction)
                    }
                }
            }

        }
    }
}