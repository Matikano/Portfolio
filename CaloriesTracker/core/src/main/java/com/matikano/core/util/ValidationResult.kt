package com.matikano.core.util

sealed class ValidationResult {
    object Success: ValidationResult()
    data class Error(val message: UiText): ValidationResult()
}
