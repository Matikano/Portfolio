package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uichallanges.subscription_manager.theme.*
import com.example.uichallanges.utils.capitalize
import com.example.uichallanges.utils.toCurrentMonthAmountString
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun AmountInfo(
    amount: Float,
    modifier: Modifier = Modifier
) {
    val date = LocalDate.now()
    val currentMonth = date.month.getDisplayName(TextStyle.FULL, Locale.US)

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Spent for ${currentMonth.capitalize()}",
            fontSize = 18.sp,
            color = TextWhite,
            fontFamily = unbounded
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = amount.toCurrentMonthAmountString(),
                fontSize = 48.sp,
                fontFamily = unbounded
            )
            Text(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Green)
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                text = "+ 5%",
                fontSize = 14.sp,
                color = TextWhite,
                fontFamily = unbounded
            )
        }
    }
}