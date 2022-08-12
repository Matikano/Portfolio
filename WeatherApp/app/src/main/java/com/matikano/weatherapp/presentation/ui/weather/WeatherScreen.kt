package com.matikano.weatherapp.presentation.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matikano.weatherapp.presentation.ui.theme.BasicGradient
import com.matikano.weatherapp.presentation.ui.weather.components.WeatherForecast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {
    val now = LocalDateTime.now()
    val state = viewModel.state

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BasicGradient),
        contentAlignment = Alignment.Center
    ) {

        state.weatherInfo?.currentWeatherData?.let{ data ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = 32.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = now.format(DateTimeFormatter.ofPattern("EEEE, d LLLL")),
                    color = Color.White,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = now.format(DateTimeFormatter.ofPattern("K:mma")).toLowerCase(),
                    color = Color.White,
                    fontSize = 36.sp
                )
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "${data.temperature}°C",
                    fontSize = 62.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                WeatherForecast(
                    state = state,
                    label = "Today"
                )

            }
        }
        if(viewModel.state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        viewModel.state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}