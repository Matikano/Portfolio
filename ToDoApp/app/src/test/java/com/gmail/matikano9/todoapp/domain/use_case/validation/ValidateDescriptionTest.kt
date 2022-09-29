package com.gmail.matikano9.todoapp.domain.use_case.validation

import com.gmail.matikano9.todoapp.util.Constants
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class ValidateDescriptionTest {

    private lateinit var validateDescription: ValidateDescription

    @Before
    fun setup(){
        validateDescription = ValidateDescription()
    }

    @Test
    fun `empty description, returns false and valid error message`(){
        val result = validateDescription("")

        assertThat(result.successful).isFalse()
        assertThat(result.errorMessage).isEqualTo(Constants.Validation.DESCRIPTION_ERROR)
    }

    @Test
    fun `valid description, returns true`(){
        val result = validateDescription("description")

        assertThat(result.successful).isTrue()
    }
}