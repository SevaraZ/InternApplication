package com.intern.presentation.weather.viewmodels


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intern.data.network.weather.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherData =
        mutableStateOf<com.example.mcore.models.weather.WeatherResponse?>(null)
    val weatherData: State<com.example.mcore.models.weather.WeatherResponse?> = _weatherData

    private val _isRunning = mutableStateOf<Boolean>(false)
    var isRunning: State<Boolean> = _isRunning

    init {
        viewModelScope.launch {
            fetchWeather()
        }
    }

    fun fetchWeather() {
        if (!_isRunning.value) {
            _isRunning.value = true
        }

        viewModelScope.launch {
            _isRunning.value = false
            val res = RetrofitInstance.api.getWeather()
            _weatherData.value = res
        }
    }
}




