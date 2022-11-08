package com.matikano.onboarding_domain.di

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
import com.matikano.onboarding_domain.use_case.summary.SaveOnBoardingNeededUseCase
import com.matikano.onboarding_domain.use_case.summary.SummaryUseCases
import com.matikano.onboarding_domain.use_case.weight.SaveWeightUseCase
import com.matikano.onboarding_domain.use_case.weight.WeightUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule {

    @Provides
    @ViewModelScoped
    fun provideGenderUseCases(
        preferences: Preferences,
        loadUserInfoUseCase: LoadUserInfoUseCase
    ): GenderUseCases = GenderUseCases(
        saveGender = SaveGenderUseCase(preferences),
        loadUserInfo = loadUserInfoUseCase
    )

    @Provides
    @ViewModelScoped
    fun provideWeightUseCases(
        preferences: Preferences,
        loadUserInfoUseCase: LoadUserInfoUseCase
    ): WeightUseCases = WeightUseCases(
        SaveWeightUseCase(preferences),
        loadUserInfo = loadUserInfoUseCase
    )

    @Provides
    @ViewModelScoped
    fun provideHeightUseCases(
        preferences: Preferences,
        filterOutDigitsUseCase: FilterOutDigitsUseCase,
        loadUserInfoUseCase: LoadUserInfoUseCase
    ): HeightUseCases = HeightUseCases(
        saveHeight = SaveHeightUseCase(preferences),
        filterOutDigits = filterOutDigitsUseCase,
        loadUserInfo = loadUserInfoUseCase
    )

    @Provides
    @ViewModelScoped
    fun provideAgeUseCases(
        preferences: Preferences,
        filterOutDigitsUseCase: FilterOutDigitsUseCase,
        loadUserInfoUseCase: LoadUserInfoUseCase
    ): AgeUseCases = AgeUseCases(
        saveAge = SaveAgeUseCase(preferences),
        filterOutDigits = filterOutDigitsUseCase,
        loadUserInfo = loadUserInfoUseCase
    )

    @Provides
    @ViewModelScoped
    fun provideGoalUseCases(
        preferences: Preferences,
        loadUserInfoUseCase: LoadUserInfoUseCase
    ): GoalTypeUseCases = GoalTypeUseCases(
        saveGoalType = SaveGoalTypeUseCase(preferences),
        loadUserInfo = loadUserInfoUseCase
    )

    @Provides
    @ViewModelScoped
    fun provideActivityUseCases(
        preferences: Preferences,
        loadUserInfoUseCase: LoadUserInfoUseCase
    ): ActivityUseCases = ActivityUseCases(
        saveActivity = SaveActivityUseCase(preferences),
        loadUserInfo = loadUserInfoUseCase
    )

    @Provides
    @ViewModelScoped
    fun provideNutrientsGoalUseCases(
        preferences: Preferences,
        validateNutrientsUseCase: ValidateNutrientsUseCase,
        filterOutDigitsUseCase: FilterOutDigitsUseCase,
        loadUserInfoUseCase: LoadUserInfoUseCase
    ): NutrientsGoalUseCases = NutrientsGoalUseCases(
        saveNutrients = SaveNutrientsUseCase(
            SaveCarbsRatioUseCase(preferences),
            SaveProteinRatioUseCase(preferences),
            SaveFatRatioUseCase(preferences)
        ),
        validateNutrients = validateNutrientsUseCase,
        filterOutDigits = filterOutDigitsUseCase,
        loadUserInfo = loadUserInfoUseCase
    )

    @Provides
    @ViewModelScoped
    fun provideSummaryUseCases(
        preferences: Preferences,
        loadUserInfoUseCase: LoadUserInfoUseCase
    ): SummaryUseCases = SummaryUseCases(
        saveOnBoardingNeeded = SaveOnBoardingNeededUseCase(preferences),
        loadUserInfo = loadUserInfoUseCase
    )

    @Provides
    @ViewModelScoped
    fun provideValidateNutrientsUseCase(): ValidateNutrientsUseCase = ValidateNutrientsUseCase()

}