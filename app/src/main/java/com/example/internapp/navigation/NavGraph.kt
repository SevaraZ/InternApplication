package com.example.internapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.internapp.ui.MainMenu
import com.example.internapp.ui.NewsScreen
import com.example.internapp.ui.WeatherScreen
import com.example.internapp.ui.StopwatchScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main_menu") {
        composable("main_menu") { MainMenu(navController) }
        composable("weather_screen") { WeatherScreen() }
        composable("stopwatch_screen") { StopwatchScreen() }
        composable("news_screen") { NewsScreen() }
    }
}