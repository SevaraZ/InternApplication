package com.intern.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.intern.presentation.mainmenu.screens.MainMenu
import com.intern.presentation.news.screens.NewsScreen
import com.intern.presentation.stopwatch.screens.StopwatchScreen
import com.intern.presentation.weather.screens.WeatherScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main_menu") {
        composable("main_menu") { MainMenu(navController) }
        composable("weather_screen") { WeatherScreen() }
        composable("stopwatch_screen") { StopwatchScreen() }
        composable("news_screen") { NewsScreen()}
    }
}