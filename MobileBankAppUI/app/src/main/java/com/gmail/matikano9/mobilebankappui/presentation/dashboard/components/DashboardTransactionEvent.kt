package com.gmail.matikano9.mobilebankappui.presentation.dashboard.components

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.matikano9.mobilebankappui.R
import com.gmail.matikano9.mobilebankappui.domain.model.Transaction
import com.gmail.matikano9.mobilebankappui.ui.theme.AlegreyaSans
import com.gmail.matikano9.mobilebankappui.ui.theme.BasicGradient
import com.gmail.matikano9.mobilebankappui.ui.theme.PositiveTransfer
import com.gmail.matikano9.mobilebankappui.ui.theme.RedGrad
import com.gmail.matikano9.mobilebankappui.utils.Extensions.format
import com.gmail.matikano9.mobilebankappui.utils.Extensions.setGradient
import kotlin.math.absoluteValue

@Composable
fun DashboardTransactionEvent(
    transaction: Transaction
) {
    Card(
        modifier = Modifier,
        elevation = 6.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier = Modifier.weight(6.5f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    modifier = Modifier
                        .size(21.dp)
                        .setGradient(BasicGradient),
                    painter = painterResource(id = transaction.account.type.iconRes),
                    contentDescription = stringResource(id = R.string.transaction_icon)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = transaction.title,
                    fontFamily = AlegreyaSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Text(
                modifier = Modifier.weight(3.5f),
                text =
                    if(transaction.amount > 0) "+\$${transaction.amount.format(2)}"
                    else "-\$${transaction.amount.absoluteValue.format(2)}",
                textAlign = TextAlign.End,
                fontFamily = AlegreyaSans,
                letterSpacing = (0.5).sp,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color =
                    if(transaction.amount > 0) PositiveTransfer
                    else Color.Black
            )

        }

    }
    Spacer(modifier = Modifier.height(12.dp))
}