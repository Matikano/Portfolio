package com.gmail.matikano9.todoapp.util

import androidx.navigation.compose.ComposeNavigator
import com.ramcosta.composedestinations.spec.Direction

sealed class UiEvent{
    object PopBackStack: UiEvent()
    data class Navigate(val destination: Direction): UiEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UiEvent()
}
