package com.matikano.complimentapp.presentation.compliment

sealed class ComplimentEvent {
    data class OnLoadCompliment(val content: String? = null): ComplimentEvent()
    object OnRefresh: ComplimentEvent()
    object OnSettingsClicked: ComplimentEvent()
}