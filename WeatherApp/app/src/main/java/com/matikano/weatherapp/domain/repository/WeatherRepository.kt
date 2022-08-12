package com.matikano.weatherapp.domain.repository

import com.matikano.weatherapp.domain.location.Location
import com.matikano.weatherapp.domain.util.Resource
import com.matikano.weatherapp.domain.weather.WeatherData
import com.matikano.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(
        location: Location
    ): Resource<WeatherInfo>
}