package com.example.internapp.data.weather

data class WeatherResponse(
    val main: Main,
    val feels_like: Main,
    val humidity: Main,
    val pressure: Main
)

data class Main(
    val temp: Double,
    val feels_Like: Double,
    val humidity: Double,
    val pressure:Int
)