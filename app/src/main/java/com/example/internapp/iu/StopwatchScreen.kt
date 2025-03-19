package com.example.internapp.iu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.internapp.viewmodel.StopwatchViewModel

@Composable
fun StopwatchScreen(viewModel: StopwatchViewModel = viewModel()) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Время: ${viewModel.time}s")
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { viewModel.startStop() }) {
                Text(if (viewModel.isRunning) "Стоп" else "Старт")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { viewModel.reset() }) {
                Text("Сброс")
            }
        }
    }
}
