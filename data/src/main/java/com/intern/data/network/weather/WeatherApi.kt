package com.intern.data.network.weather

import com.example.core.models.weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double = 41.3123363,
        @Query("lon") lon: Double = 69.2787079,
        @Query("appid") apiKey: String = "5842e585719c5f442d3fe0d7cf7de6dd",
        @Query("units") units: String = "metric"
    ): com.example.core.models.weather.WeatherResponse
}