package com.matikano.onboarding_domain.use_case.nutrients_goal

import javax.inject.Inject

class SaveNutrientsUseCase @Inject constructor(
    private val saveCarbsRatio: SaveCarbsRatioUseCase,
    private val saveProteinRatio: SaveProteinRatioUseCase,
    private val saveFatRatio: SaveFatRatioUseCase
) {
    suspend operator fun invoke(
        carbsRatioString: String,
        proteinRatioString: String,
        fatRatioString: String,
    ) {
        saveCarbsRatio(carbsRatioString.toFloat() / 100f)
        saveProteinRatio(proteinRatioString.toFloat()/ 100f)
        saveFatRatio(fatRatioString.toFloat() / 100f)
    }
}