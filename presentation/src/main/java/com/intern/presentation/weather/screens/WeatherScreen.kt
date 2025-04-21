package com.intern.presentation.weather.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.intern.presentation.weather.viewmodels.WeatherViewModel


@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    val weatherData by viewModel.weatherData
    val isRunning by viewModel.isRunning

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = MaterialTheme.colorScheme.secondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Current Weather ⛅\uFE0F",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        WeatherCard(weatherData, isRunning)
        RefreshButton(viewModel)
        LoadingIndicator()
    }
}

@Composable
fun WeatherCard(
    weatherData: com.example.mcore.models.weather.WeatherResponse?,
    isRunning: Boolean
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        if (isRunning) {
            LoadingIndicator()
        } else {
            WeatherDetails(weatherData)
        }
    }
}

@Composable
fun LoadingIndicator() {
    Text(
        "Loading",
        fontSize = 24.sp,
        color = MaterialTheme.colorScheme.tertiary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(32.dp)

    )
}

@Composable
fun WeatherDetails(weatherData: com.example.mcore.models.weather.WeatherResponse?) {
    Text(
        "\uD83C\uDF21\uFE0FTemperature: ${weatherData?.main?.temp} °C",
        fontSize = 24.sp,
        color = MaterialTheme.colorScheme.tertiary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(16.dp)

    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            "Feels like: ${weatherData?.main?.feels_like} °C",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
    }

    Text(
        "Humidity: ${weatherData?.main?.humidity}%",
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier.padding(12.dp)
    )

    Text(
        "Pressure: ${weatherData?.main?.pressure} ",
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier.padding(12.dp)
    )
}

@Composable
fun RefreshButton(viewModel: WeatherViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { viewModel.fetchWeather() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                "Refresh",
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

