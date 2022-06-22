package com.gmail.matikano9.todoapp.domain.validation

import com.gmail.matikano9.todoapp.util.Constants.Validation.DESCRIPTION_ERROR

class ValidateDescription {
    inline operator fun invoke(description: String): ValidationResult {
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