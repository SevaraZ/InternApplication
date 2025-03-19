package com.example.internapp.iu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MainMenu(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("weather_screen") }) {
            Text("Weather")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("stopwatch_screen") }) {
            Text("Stopwatch")
        }
    }
}