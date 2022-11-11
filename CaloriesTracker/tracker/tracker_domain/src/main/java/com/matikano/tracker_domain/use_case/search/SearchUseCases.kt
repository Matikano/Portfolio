package com.matikano.tracker_domain.use_case.search

import com.matikano.core.domain.use_case.FilterOutDigitsUseCase

data class SearchUseCases(
    val searchFood: SearchFoodUseCase,
    val trackFood: TrackFoodUseCase,
    val filterOutDigits: FilterOutDigitsUseCase
)
