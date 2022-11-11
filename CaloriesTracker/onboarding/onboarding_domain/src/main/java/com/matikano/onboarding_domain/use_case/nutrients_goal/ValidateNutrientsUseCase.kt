package com.matikano.onboarding_domain.use_case.nutrients_goal

import com.matikano.core.R
import com.matikano.core.util.UiText
import com.matikano.core.util.ValidationResult

class ValidateNutrientsUseCase {

    suspend operator fun invoke(
        carbsString: String,
        proteinString: String,
        fatString: String,
    ): ValidationResult {
        val errorEmptyValue = ValidationResult.Error(UiText.StringResource(R.string.error_empty_values))

        val carbs: Int = carbsString.toIntOrNull() ?: return errorEmptyValue
        val proteins: Int = proteinString.toIntOrNull() ?: return errorEmptyValue
        val fat: Int = fatString.toIntOrNull() ?: return errorEmptyValue

        if(carbs + proteins + fat != 100){
            return ValidationResult.Error(UiText.StringResource(R.string.error_not_100_percent))
        }

        return ValidationResult.Success
    }
}