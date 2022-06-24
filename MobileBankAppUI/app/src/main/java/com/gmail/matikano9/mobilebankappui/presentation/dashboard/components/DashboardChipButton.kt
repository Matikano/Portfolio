package com.gmail.matikano9.mobilebankappui.presentation.dashboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.matikano9.mobilebankappui.ui.theme.AlegreyaSans
import com.gmail.matikano9.mobilebankappui.ui.theme.BasicGradient
import com.gmail.matikano9.mobilebankappui.ui.theme.Surface

@Composable
fun DashboardChipButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .background(Color.Transparent)
            .shadow(
                2.dp,
                RoundedCornerShape(16.dp)
            ),
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            2.dp,
            BasicGradient
        )
    ) {
        Text(
            modifier = Modifier
                .padding(6.dp),
            text = title,
            fontFamily = AlegreyaSans,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
    
    Spacer(modifier = Modifier.width(12.dp))
}