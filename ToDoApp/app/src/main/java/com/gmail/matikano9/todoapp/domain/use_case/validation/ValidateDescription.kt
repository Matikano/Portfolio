package com.gmail.matikano9.todoapp.domain.use_case.validation

import com.gmail.matikano9.todoapp.util.Constants.Validation.DESCRIPTION_ERROR
import com.gmail.matikano9.todoapp.util.ValidationResult

class ValidateDescription {
    operator fun invoke(description: String): ValidationResult {
        return if(description.isBlank() || description.isEmpty())
            ValidationResult(
                successful = false,
                errorMessage = DESCRIPTION_ERROR
            )
        else
            ValidationResult(
                successful = true
            )
    }
}