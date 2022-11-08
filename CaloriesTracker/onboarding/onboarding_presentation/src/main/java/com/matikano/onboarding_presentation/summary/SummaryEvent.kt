package com.matikano.onboarding_presentation.summary

sealed class SummaryEvent {
    object OnConfirmClick: SummaryEvent()
    object OnNavigateBackClick: SummaryEvent()
}
