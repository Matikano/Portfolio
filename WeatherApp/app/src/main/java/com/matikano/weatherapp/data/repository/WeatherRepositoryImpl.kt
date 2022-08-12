package com.matikano.weatherapp.data.repository

import com.matikano.weatherapp.data.mappers.toWeatherInfo
import com.matikano.weatherapp.data.remote.WeatherApi
import com.matikano.weatherapp.domain.location.Location
import com.matikano.weatherapp.domain.repository.WeatherRepository
import com.matikano.weatherapp.domain.util.Resource
import com.matikano.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(
        location: Location
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = location.latitude,
                    long = location.longitude,
                ).toWeatherInfo()
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occured.")
        }
    }
}