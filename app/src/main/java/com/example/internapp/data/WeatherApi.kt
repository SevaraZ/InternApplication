package com.example.internapp.data



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class WeatherResponse(val main: Main)
data class Main(val temp: Float)

interface WeatherApi {
    @GET("data/2.5/weather?q=Tashkent&appid=YOUR_API_KEY&units=metric")
    suspend fun getWeather(): WeatherResponse
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.openweathermap.org/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val weatherApi = retrofit.create(WeatherApi::class.java)
