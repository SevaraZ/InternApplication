package com.example.internapp.data

data class WeatherResponse(
    val main: Main
)

data class Main(
    val temp: Double
)