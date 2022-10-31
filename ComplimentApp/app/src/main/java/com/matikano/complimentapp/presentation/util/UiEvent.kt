package com.matikano.complimentapp.presentation.util

sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(val content: String): UiEvent()
}
