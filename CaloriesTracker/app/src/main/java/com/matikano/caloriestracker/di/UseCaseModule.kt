package com.matikano.caloriestracker.di

import com.matikano.core.domain.preferences.Preferences
import com.matikano.core.domain.use_case.FilterOutDigitsUseCase
import com.matikano.core.domain.use_case.LoadUserInfoUseCase
import com.matikano.onboarding_domain.use_case.activity.ActivityUseCases
import com.matikano.onboarding_domain.use_case.activity.SaveActivityUseCase
import com.matikano.onboarding_domain.use_case.age.AgeUseCases
import com.matikano.onboarding_domain.use_case.age.SaveAgeUseCase
import com.matikano.onboarding_domain.use_case.gender.GenderUseCases
import com.matikano.onboarding_domain.use_case.gender.SaveGenderUseCase
import com.matikano.onboarding_domain.use_case.goal_type.GoalTypeUseCases
import com.matikano.onboarding_domain.use_case.goal_type.SaveGoalTypeUseCase
import com.matikano.onboarding_domain.use_case.height.HeightUseCases
import com.matikano.onboarding_domain.use_case.height.SaveHeightUseCase
import com.matikano.onboarding_domain.use_case.nutrients_goal.*
import com.matikano.onboarding_domain.use_case.weight.SaveWeightUseCase
import com.matikano.onboarding_domain.use_case.weight.WeightUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideFilterOutDigitsUseCase(): FilterOutDigitsUseCase = FilterOutDigitsUseCase()

    @Provides
    @ViewModelScoped
    fun provideLoadUserInfoUseCase(
        preferences: Preferences
    ): LoadUserInfoUseCase = LoadUserInfoUseCase(preferences)

}