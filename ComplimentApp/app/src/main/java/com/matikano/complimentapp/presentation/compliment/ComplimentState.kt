package com.matikano.complimentapp.presentation.compliment

import com.matikano.complimentapp.domain.compliment.Compliment

data class ComplimentState(
    val compliment: Compliment? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
