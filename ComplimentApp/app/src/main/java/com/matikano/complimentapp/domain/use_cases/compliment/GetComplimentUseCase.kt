package com.matikano.complimentapp.domain.use_cases.compliment

import com.matikano.complimentapp.domain.compliment.Compliment
import com.matikano.complimentapp.domain.repository.ComplimentRepository
import com.matikano.complimentapp.domain.util.Resource
import javax.inject.Inject

class GetComplimentUseCase @Inject constructor (
    private val repository : ComplimentRepository
) {
    suspend operator fun invoke(): Resource<Compliment>
        = repository.getCompliment()
}