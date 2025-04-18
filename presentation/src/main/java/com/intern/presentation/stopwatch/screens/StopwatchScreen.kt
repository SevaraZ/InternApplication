package com.intern.presentation.stopwatch.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.intern.presentation.stopwatch.viewmodels.StopwatchViewModel


@Composable
fun StopwatchScreen(viewModel: StopwatchViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.secondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = viewModel.getFormattedTime(),
                fontSize = 48.sp,
                color = MaterialTheme.colorScheme.tertiary,
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
                    containerColor = if (viewModel.isRunning) Color.Red else MaterialTheme.colorScheme.primary

                )
            ) {
                Text(if (viewModel.isRunning) "Stop"
                else "Start")
            }

            Button(
                onClick = { viewModel.reset() },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    "Reset",
                    color = MaterialTheme.colorScheme.tertiary

                )
            }
        }
    }
}

