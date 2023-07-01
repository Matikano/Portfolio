package com.example.uichallanges.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.uichallanges.subscription_manager.theme.subsFont
import com.example.uichallanges.subscription_manager.theme.unbounded

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = subsFont,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)