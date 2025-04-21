package com.intern.presentation.stopwatch.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StopwatchViewModel : ViewModel() {


    var time by mutableLongStateOf(0L)
        private set

    var isRunning by mutableStateOf(false)
        private set

    var laps by mutableStateOf(listOf<String>())
        private set

    fun startStop() {
        if (isRunning) {
            isRunning = false
        } else {
            isRunning = true
            viewModelScope.launch {
                while (isRunning) {
                    delay(1000)
                    time++
                }
            }
        }
    }

    fun reset() {
        time = 0
        isRunning = false
        laps = emptyList()
    }

    fun recordLap() {
        val currentLap = getFormattedTime()
        laps = listOf(currentLap) + laps
    }

    fun getFormattedTime(): String {
        val hours = (time / 3600).toInt()
        val minutes = ((time % 3600) / 60).toInt()
        val seconds = (time % 60).toInt()
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}



