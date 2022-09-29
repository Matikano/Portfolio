package com.gmail.matikano9.todoapp.domain.use_case.validation

import com.gmail.matikano9.todoapp.util.Constants
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class ValidateTitleTest {

    private lateinit var validateTitle: ValidateTitle

    @Before
    fun setup(){
        validateTitle = ValidateTitle()
    }

    @Test
    fun `empty title, returns false and valid error message`(){
        val result = validateTitle("")

        assertThat(result.successful).isFalse()
        assertThat(result.errorMessage).isEqualTo(Constants.Validation.TITLE_ERROR)
    }

    @Test
    fun `valid title, returns true`(){
        val result = validateTitle("title")

        assertThat(result.successful).isTrue()
    }
}