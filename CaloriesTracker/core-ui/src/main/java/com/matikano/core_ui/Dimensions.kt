package com.matikano.core_ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimensions(
    val default: Dp = 0.dp,
    val shadowPadding: Dp = 2.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp,
    val topAppBarHeight: Dp = 56.dp
)

val LocalSpacing = compositionLocalOf {
    Dimensions()
}

val defaultBorderStroke = 1.dp

object UnitDisplay {
    val amountTextSize = 20.sp
    val unitTextSize = 14.sp
}

object NutrientsHeader {
    val nutrientBarInfoSize = 90.dp
    val nutrientBarInfoStroke = 8.dp
    val nutrientsBarHeight = 30.dp

    val shape = RoundedCornerShape(
        bottomStart = 50.dp,
        bottomEnd = 50.dp
    )

    val unitDisplayAmountTextSize = 40.sp
    val infoIconSize = 20.dp
}

object TrackableFoodItem {
    const val nameMaxLines = 1
    val shadowElevation = 1.dp
    val imageSize = 100.dp
    val imageClip = RoundedCornerShape(topStart = 5.dp)

    val nutrientInfoAmountTextSize = 16.sp
    val nutrientInfoUnitTextSize = 12.sp

    val amountTextFieldBorder = 0.5.dp
}

object TrackedFoodItem {
    const val itemNameMaxLines = 2

    val itemHeight = 100.dp
    val shadowElevation = 1.dp

    val imageShape = RoundedCornerShape(
        topStart = 5.dp,
        bottomStart = 5.dp
    )

    val nutrientInfoAmountTextSize = 16.sp
    val nutrientInfoUnitTextSize = 12.sp
}

object ExpandableMeal {
    val unitDisplayAmountTextSize = 30.sp
}

object SearchTextField {
    val shadowElevation = 2.dp
}
