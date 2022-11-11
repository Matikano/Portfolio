package com.matikano.tracker_domain.use_case.tracker_overview

import com.matikano.tracker_domain.model.TrackedFood
import com.matikano.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDateUseCase(
    private val repository: TrackerRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> =
        repository.getFoodsForDate(date)

}