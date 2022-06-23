package com.gmail.matikano9.todoapp.domain.use_case.validation

import com.gmail.matikano9.todoapp.util.Constants.Validation.TITLE_ERROR
import com.gmail.matikano9.todoapp.util.ValidationResult

class ValidateTitle {
    operator fun invoke(title: String): ValidationResult {
        return if(title.isBlank() || title.isEmpty())
            ValidationResult(
                successful = false,
                errorMessage = TITLE_ERROR
            )
        else
            ValidationResult(
                successful = true
            )
    }
}