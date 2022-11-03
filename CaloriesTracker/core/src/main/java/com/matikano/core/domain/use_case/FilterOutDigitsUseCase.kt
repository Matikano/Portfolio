package com.matikano.core.domain.use_case

import javax.inject.Inject

class FilterOutDigitsUseCase {
    operator fun invoke(text: String): String = text.filter {
        it.isDigit()
    }
}