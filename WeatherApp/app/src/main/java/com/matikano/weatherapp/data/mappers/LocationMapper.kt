package com.matikano.weatherapp.data.mappers

import com.matikano.weatherapp.domain.location.Location
import com.matikano.weatherapp.domain.location.LocationTracker

fun Location.toAndroidLocation(): android.location.Location =
    android.location.Location("").apply {
        latitude = this.latitude
        longitude = this.longitude
    }

fun android.location.Location.toLocation(): Location =
    Location(
        latitude,
        longitude
    )
