package com.matikano.complimentapp.data.repository

import com.matikano.complimentapp.data.mappers.toCompliment
import com.matikano.complimentapp.data.remote.ComplimentApi
import com.matikano.complimentapp.domain.compliment.Compliment
import com.matikano.complimentapp.domain.repository.ComplimentRepository
import com.matikano.complimentapp.domain.util.Resource
import com.matikano.complimentapp.util.Constants.Errors.UNKNOWN_API_ERROR
import javax.inject.Inject

class ComplimentRepositoryImpl @Inject constructor(
    private val api: ComplimentApi
): ComplimentRepository {

    override suspend fun getCompliment(): Resource<Compliment> =
        try {
            Resource.Success(
                data = api.getCompliment().toCompliment()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: UNKNOWN_API_ERROR)
        }
}