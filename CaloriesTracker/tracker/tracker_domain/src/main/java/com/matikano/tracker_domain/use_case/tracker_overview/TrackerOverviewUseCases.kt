package com.matikano.tracker_domain.use_case.tracker_overview

import com.matikano.tracker_domain.use_case.search.SearchFoodUseCase
import com.matikano.tracker_domain.use_case.search.TrackFoodUseCase

data class TrackerOverviewUseCases(
    val calculateMealNutrients: CalculateMealNutrientsUseCase,
    val deleteTrackedFood: DeleteTrackedFoodUseCase,
    val getFoodsForDate: GetFoodsForDateUseCase,
)
