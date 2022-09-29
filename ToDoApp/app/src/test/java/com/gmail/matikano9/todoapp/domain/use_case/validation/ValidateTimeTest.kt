package com.gmail.matikano9.todoapp.domain.use_case.validation

import com.gmail.matikano9.todoapp.util.Constants
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ValidateTimeTest {

    private lateinit var validateTime: ValidateTime

    @Before
    fun setup(){
        validateTime = ValidateTime()
    }

    @Test
    fun `dueTimeString empty, returns false and valid error message`(){
        val result = validateTime("")

        assertThat(result.successful).isFalse()
        assertThat(result.errorMessage).isEqualTo(Constants.Validation.DUE_TIME_EMPTY)
    }

    @Test
    fun `dueTimeString valid, returns true`(){
        val result = validateTime("12312")

        assertThat(result.successful).isTrue()
    }
}