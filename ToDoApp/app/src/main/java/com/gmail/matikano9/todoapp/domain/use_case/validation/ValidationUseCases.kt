package com.gmail.matikano9.todoapp.domain.use_case.validation

import javax.inject.Inject

data class ValidationUseCases @Inject constructor(
    val validateTitle: ValidateTitle,
    val validateDescription: ValidateDescription,
    val validateDate: ValidateDate,
    val validateTime: ValidateTime
)