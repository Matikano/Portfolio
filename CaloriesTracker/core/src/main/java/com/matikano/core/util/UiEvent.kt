package com.matikano.core.util


sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(val message: UiText): UiEvent()
    object PopBackStack: UiEvent()
}