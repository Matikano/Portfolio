package com.gmail.matikano9.todoapp.util

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)
