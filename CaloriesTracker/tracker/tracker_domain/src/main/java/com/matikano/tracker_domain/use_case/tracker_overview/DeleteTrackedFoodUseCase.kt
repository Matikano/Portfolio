package com.matikano.tracker_domain.use_case.tracker_overview

import com.matikano.tracker_domain.model.TrackedFood
import com.matikano.tracker_domain.repository.TrackerRepository

class DeleteTrackedFoodUseCase(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(food: TrackedFood) = repository.deleteTrackedFood(food)
}