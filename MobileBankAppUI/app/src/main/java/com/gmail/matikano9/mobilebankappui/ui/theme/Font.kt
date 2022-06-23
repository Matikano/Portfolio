package com.gmail.matikano9.mobilebankappui.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.gmail.matikano9.mobilebankappui.R


val AlegreyaSansMedium = Font(
    R.font.alegreya_sans_medium,
    FontWeight.Medium
)

val AlegreyaSansRegular = Font(
    R.font.alegreya_sans_regular,
    FontWeight.Normal
)

val AlegreyaSansExtraBold = Font(
    R.font.alegreya_sans_extra_bold,
    FontWeight.ExtraBold
)

val AlegreyaSansThin = Font(
    R.font.alegreya_sans_thin,
    FontWeight.Thin
)

val AlegreyaSansBold = Font(
    R.font.alegreya_sans_bold,
    FontWeight.Bold
)


val AlegreyaSans = FontFamily(
    listOf(
        AlegreyaSansMedium,
        AlegreyaSansRegular,
        AlegreyaSansThin,
        AlegreyaSansBold,
        AlegreyaSansExtraBold
    )
)