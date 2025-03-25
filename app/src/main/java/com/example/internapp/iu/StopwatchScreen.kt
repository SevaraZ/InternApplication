package com.example.internapp.iu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.internapp.viewmodel.StopwatchViewModel

@Composable
fun StopwatchScreen(viewModel: StopwatchViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Text(
                text = viewModel.getFormattedTime(),
                fontSize = 48.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.startStop() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (viewModel.isRunning) Color.Red else Color.Gray

                )
            ) {
                Text(if (viewModel.isRunning) "Стоп" else "Старт")
            }

            Button(
                onClick = { viewModel.reset() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Сброс")
            }
        }
    }
}

