package com.matikano.tracker_domain.di

import com.matikano.core.domain.preferences.Preferences
import com.matikano.core.domain.use_case.FilterOutDigitsUseCase
import com.matikano.tracker_domain.repository.TrackerRepository
import com.matikano.tracker_domain.use_case.*
import com.matikano.tracker_domain.use_case.search.SearchFoodUseCase
import com.matikano.tracker_domain.use_case.search.SearchUseCases
import com.matikano.tracker_domain.use_case.search.TrackFoodUseCase
import com.matikano.tracker_domain.use_case.tracker_overview.CalculateMealNutrientsUseCase
import com.matikano.tracker_domain.use_case.tracker_overview.DeleteTrackedFoodUseCase
import com.matikano.tracker_domain.use_case.tracker_overview.GetFoodsForDateUseCase
import com.matikano.tracker_domain.use_case.tracker_overview.TrackerOverviewUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @Provides
    @ViewModelScoped
    fun provideTrackerOverviewUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerOverviewUseCases = TrackerOverviewUseCases(
        getFoodsForDate = GetFoodsForDateUseCase(repository),
        deleteTrackedFood = DeleteTrackedFoodUseCase(repository),
        calculateMealNutrients = CalculateMealNutrientsUseCase(preferences)
    )

    @Provides
    @ViewModelScoped
    fun provideSearchUseCases(
        repository: TrackerRepository,
        filterOutDigitsUseCase: FilterOutDigitsUseCase
    ): SearchUseCases = SearchUseCases(
        trackFood = TrackFoodUseCase(repository),
        searchFood = SearchFoodUseCase(repository),
        filterOutDigits = filterOutDigitsUseCase
    )
}