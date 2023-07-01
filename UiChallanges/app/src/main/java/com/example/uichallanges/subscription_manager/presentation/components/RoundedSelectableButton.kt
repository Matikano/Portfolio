package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uichallanges.subscription_manager.theme.BlueMain
import com.example.uichallanges.subscription_manager.theme.MediumGrey
import com.example.uichallanges.subscription_manager.theme.TextWhite
import com.example.uichallanges.subscription_manager.theme.unbounded

@Composable
fun RoundedSelectableButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    selected: Boolean = false
) {
    Row(
        modifier = modifier
            .height(36.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .background(if (selected) BlueMain else MediumGrey)
            .padding(vertical = 4.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = TextWhite,
            fontFamily = unbounded,
            fontSize = 14.sp
        )
    }
}