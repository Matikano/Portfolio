package com.gmail.matikano9.todoapp.domain.validation

import com.gmail.matikano9.todoapp.util.Constants
import com.gmail.matikano9.todoapp.util.Constants.Validation.DUE_TIME_EMPTY

class ValidateTime {
    inline operator fun invoke(dueTimeString: String): ValidationResult {
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