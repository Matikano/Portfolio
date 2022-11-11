package com.matikano.tracker_presentation.search

import com.matikano.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)

fun TrackableFood.toUiState(): TrackableFoodUiState =
    TrackableFoodUiState(
        food = this
    )
