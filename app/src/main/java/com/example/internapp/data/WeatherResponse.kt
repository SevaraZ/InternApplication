package com.example.internapp.data

data class WeatherResponse(
    val main: Main,
    val feelsLike: Main,
    val humidity: Main,
    val windSpeed: Main
)

data class Main(
    val temp: Double,
    val feelsLike: Double,
    val humidity: Double,
    val windSpeed:Double
)