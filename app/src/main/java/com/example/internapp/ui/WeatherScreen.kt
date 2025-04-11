package com.example.internapp.ui

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.internapp.R
import com.example.internapp.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    val weatherData by viewModel.weatherData
    val isRunning by viewModel.isRunning

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {

            if (isRunning) {
                Text(
                    stringResource(R.string.loading),
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(32.dp)
                        .align(Alignment.CenterHorizontally))
            } else {
                Text("Temperature: ${weatherData?.main?.temp} °C",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally))
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),

                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Text("Feels like: ${weatherData?.main?.feels_Like} °C",
                        fontSize = 20.sp,
                        color = Color.White


                    )
                }
                Text(
                    "Humidity: ${weatherData?.main?.humidity}%",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(12.dp)
                )
                Text("Pressure: ${weatherData?.main?.pressure} ",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(12.dp)

                )

            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = { viewModel.fetchWeather() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)

            ) {
                Text(stringResource(R.string.refresh))
            }
        }

    }
}


