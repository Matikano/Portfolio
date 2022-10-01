package com.gmail.matikano9.todoapp.domain.use_case.validation

import com.gmail.matikano9.todoapp.util.Constants
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class ValidateDateTest {

    private lateinit var validateDate: ValidateDate

    @Before
    fun setup() {
        validateDate = ValidateDate()
    }

    @Test
    fun emptyString_returnsFalseAndCorrectErrorMessage(){
        val now = LocalDate.now()
        val result = validateDate("", now)
        assertThat(result.successful).isFalse()
        assertThat(result.errorMessage).isEqualTo(Constants.Validation.DUE_DATE_EMPTY)
    }

    @Test
    fun dateBeforeCurrent_returnsFalseAndCorrectErrorMessage(){
        val date = LocalDate.now().minusDays(1)
        val result = validateDate(date.toString(), date)
        assertThat(result.successful).isFalse()
        assertThat(result.errorMessage).isEqualTo(Constants.Validation.DUE_DATE_INVALID)
    }

    @Test
    fun correctString_returnsTrue(){
        val result = validateDate("correctTestString", LocalDate.now().plusDays(1))
        assertThat(result.successful).isTrue()
    }
}