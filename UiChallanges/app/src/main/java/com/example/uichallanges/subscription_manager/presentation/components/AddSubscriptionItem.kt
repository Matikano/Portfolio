package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uichallanges.subscription_manager.theme.BlueMain
import com.example.uichallanges.subscription_manager.theme.TextWhite
import com.example.uichallanges.subscription_manager.theme.unbounded
import com.example.uichallanges.ui.theme.overlappingOffset

@Composable
fun AddSubscriptionItem(
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(BlueMain)
            .padding(vertical = 24.dp, horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Add a subscription",
                color = TextWhite,
                fontFamily = unbounded,
                fontSize = 18.sp
            )
            DashedCircleButton(
                onClick = onClick,
                icon = Icons.Default.Add
            )
        }
        Spacer(modifier = Modifier.height(overlappingOffset))
    }
}