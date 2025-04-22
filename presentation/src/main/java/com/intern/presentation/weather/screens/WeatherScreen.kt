package com.intern.presentation.weather.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.intern.presentation.weather.viewmodels.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel(),
                  navController: NavHostController) {
    val weatherData by viewModel.weatherData
    val isRunning by viewModel.isRunning

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
            .padding(16.dp)
            .background(color = MaterialTheme.colorScheme.secondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Text(
            text = "Current Weather ⛅\uFE0F",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        WeatherCard(weatherData, isRunning)
        RefreshButton(viewModel)

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
        "Loading...",
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
        "\uD83D\uDCA7Humidity: ${weatherData?.main?.humidity}%",
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



