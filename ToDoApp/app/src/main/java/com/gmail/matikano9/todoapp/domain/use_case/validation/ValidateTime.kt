package com.gmail.matikano9.todoapp.domain.use_case.validation

import com.gmail.matikano9.todoapp.util.Constants.Validation.DUE_TIME_EMPTY
import com.gmail.matikano9.todoapp.util.ValidationResult

class ValidateTime {
    operator fun invoke(dueTimeString: String): ValidationResult {
        return if(dueTimeString.isBlank() || dueTimeString.isEmpty())
            ValidationResult(
                successful = false,
                errorMessage = DUE_TIME_EMPTY
            )
        else
            ValidationResult(
                successful = true
            )
    }
}