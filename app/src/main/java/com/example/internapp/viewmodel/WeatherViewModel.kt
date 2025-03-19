package com.example.internapp.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internapp.data.weatherApi
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    var temperature by mutableStateOf("Загрузка...")
        private set

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather()
                temperature = "Температура: ${response.main.temp}°C"
            } catch (e: Exception) {
                temperature = "Ошибка загрузки"
            }
        }
    }
}
