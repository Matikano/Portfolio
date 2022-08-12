package com.matikano.weatherapp.presentation.ui.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matikano.weatherapp.presentation.ui.weather.WeatherState
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt
import com.matikano.weatherapp.R

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier,
    label: String
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let{ data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = label,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(content = {
                items(data) { weatherData ->
                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                        .height(100.dp)
                    )
                    Spacer(modifier = Modifier.width(32.dp))
                }
            })
        }
        
    }
    
}