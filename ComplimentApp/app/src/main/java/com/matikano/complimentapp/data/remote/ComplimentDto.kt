package com.matikano.complimentapp.data.remote

import com.squareup.moshi.Json

data class ComplimentDto(
    @field:Json(name = "compliment")
    val compliment: String
)
