package com.intern.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.internapp.ui.theme.InternAppTheme
import com.intern.presentation.mainmenu.screens.MainMenu
import com.intern.presentation.mainmenu.viewModel.MenuViewModel
import com.intern.presentation.news.screens.NewsScreen
import com.intern.presentation.stopwatch.screens.StopwatchScreen
import com.intern.presentation.weather.screens.WeatherScreen

@Composable
fun InternAppScreen(viewModel: MenuViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val darkTheme = viewModel.themeModeState.collectAsState(false)

    InternAppTheme(darkTheme = darkTheme.value) {
        NavHost(navController = navController, startDestination = "main_menu") {
            composable("main_menu") { MainMenu(navController) }
            composable("weather_screen") { WeatherScreen() }
            composable("stopwatch_screen") { StopwatchScreen() }
            composable("news_screen") { NewsScreen()}

        }
    }
}