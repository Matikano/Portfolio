package com.gmail.matikano9.todoapp.domain.validation

import javax.inject.Inject

data class Validations @Inject constructor(
    val validateTitle: ValidateTitle,
    val validateDescription: ValidateDescription,
    val validateDate: ValidateDate,
    val validateTime: ValidateTime
)