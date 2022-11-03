package com.matikano.caloriestracker.di

import com.matikano.core.domain.preferences.Preferences
import com.matikano.core.domain.use_case.FilterOutDigitsUseCase
import com.matikano.onboarding_domain.use_case.activity.ActivityUseCases
import com.matikano.onboarding_domain.use_case.activity.SaveActivityUseCase
import com.matikano.onboarding_domain.use_case.age.AgeUseCases
import com.matikano.onboarding_domain.use_case.age.SaveAgeUseCase
import com.matikano.onboarding_domain.use_case.gender.GenderUseCases
import com.matikano.onboarding_domain.use_case.gender.SaveGenderUseCase
import com.matikano.onboarding_domain.use_case.goal.GoalUseCases
import com.matikano.onboarding_domain.use_case.goal.SaveGoalUseCase
import com.matikano.onboarding_domain.use_case.height.HeightUseCases
import com.matikano.onboarding_domain.use_case.height.SaveHeightUseCase
import com.matikano.onboarding_domain.use_case.weight.SaveWeightUseCase
import com.matikano.onboarding_domain.use_case.weight.WeightUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideFilterOutDigitsUseCase(): FilterOutDigitsUseCase = FilterOutDigitsUseCase()

    @Provides
    @ViewModelScoped
    fun provideGenderUseCases(
        preferences: Preferences
    ): GenderUseCases = GenderUseCases(
        SaveGenderUseCase(preferences),
    )

    @Provides
    @ViewModelScoped
    fun provideWeightUseCases(
        preferences: Preferences
    ): WeightUseCases = WeightUseCases(
        SaveWeightUseCase(preferences)
    )

    @Provides
    @ViewModelScoped
    fun provideHeightUseCases(
        preferences: Preferences,
        filterOutDigitsUseCase: FilterOutDigitsUseCase
    ): HeightUseCases = HeightUseCases(
        SaveHeightUseCase(preferences),
        filterOutDigitsUseCase
    )

    @Provides
    @ViewModelScoped
    fun provideAgeUseCases(
        preferences: Preferences,
        filterOutDigitsUseCase: FilterOutDigitsUseCase
    ): AgeUseCases = AgeUseCases(
        SaveAgeUseCase(preferences),
        filterOutDigitsUseCase
    )

    @Provides
    @ViewModelScoped
    fun provideGoalUseCases(
        preferences: Preferences
    ): GoalUseCases = GoalUseCases(
        SaveGoalUseCase(preferences)
    )

    @Provides
    @ViewModelScoped
    fun provideActivityUseCases(
        preferences: Preferences
    ): ActivityUseCases = ActivityUseCases(
        SaveActivityUseCase(preferences)
    )



}