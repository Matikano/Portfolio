package com.matikano.weatherapp.domain.location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}