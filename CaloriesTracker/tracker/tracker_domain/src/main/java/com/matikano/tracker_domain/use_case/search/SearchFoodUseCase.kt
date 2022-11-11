package com.matikano.tracker_domain.use_case.search

import com.matikano.tracker_domain.model.TrackableFood
import com.matikano.tracker_domain.repository.TrackerRepository

class SearchFoodUseCase(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> =
        if (query.isBlank()) {
             Result.success(emptyList())
        } else repository.searchFoods(
            query = query.trim(),
            page = page,
            pageSize = pageSize
        )
}

