package com.matikano.complimentapp.presentation.compliment

sealed class ComplimentEvent {
    data class OnLoadCompliment(val content: String?): ComplimentEvent()
    object OnRefresh: ComplimentEvent()
}