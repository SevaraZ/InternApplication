package com.example.internapp.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StopwatchViewModel : ViewModel() {
    var time by mutableStateOf(0L)
        private set
    var isRunning by mutableStateOf(false)
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
    }
}