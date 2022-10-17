package com.matikano.complimentapp.domain.repository

import com.matikano.complimentapp.domain.compliment.Compliment
import com.matikano.complimentapp.domain.util.Resource

interface ComplimentRepository {

    suspend fun  getCompliment(): Resource<Compliment>
}