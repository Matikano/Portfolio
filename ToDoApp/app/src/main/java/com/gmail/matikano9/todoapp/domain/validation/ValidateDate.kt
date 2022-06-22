package com.gmail.matikano9.todoapp.domain.validation

import com.gmail.matikano9.todoapp.util.Constants
import com.gmail.matikano9.todoapp.util.Constants.Validation.DUE_DATE_EMPTY
import com.gmail.matikano9.todoapp.util.Constants.Validation.DUE_DATE_INVALID
import java.time.LocalDate

class ValidateDate {
    inline operator fun invoke(dateString: String, date: LocalDate): ValidationResult {
        return when {
            dateString.isBlank() || dateString.isEmpty() ->
                ValidationResult(
                    successful = false,
                    errorMessage = DUE_DATE_EMPTY
                )
            date.isBefore(LocalDate.now()) ->
                ValidationResult(
                    successful = false,
                    errorMessage = DUE_DATE_INVALID
                )
            else ->
                ValidationResult(
                    successful = true
                )
        }
    }
}