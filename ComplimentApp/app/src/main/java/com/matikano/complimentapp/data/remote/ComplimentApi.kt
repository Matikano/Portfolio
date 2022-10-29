package com.matikano.complimentapp.data.remote

import retrofit2.http.GET


interface ComplimentApi {

    @GET("api")
    suspend fun getCompliment(): ComplimentDto

    companion object {
        const val BASE_URL = "https://complimentr.com/"
    }
}