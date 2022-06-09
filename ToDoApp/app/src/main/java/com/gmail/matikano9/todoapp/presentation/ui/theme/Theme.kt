package com.gmail.matikano9.todoapp.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = lightColors(

    primary = primaryDark,
    onPrimary = onPrimary,
    primaryVariant = primaryDark,

    secondary = secondary,
    onSecondary = onSecondary,

    background = background,
    onBackground = onBackground,
    surface = primaryLight,
    onSurface = onSurface
)

@Composable
fun ToDoAppTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}