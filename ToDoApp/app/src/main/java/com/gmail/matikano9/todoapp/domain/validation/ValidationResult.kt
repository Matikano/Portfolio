package com.gmail.matikano9.todoapp.domain.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)
