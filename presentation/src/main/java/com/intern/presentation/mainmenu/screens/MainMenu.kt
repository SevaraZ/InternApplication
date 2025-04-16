package com.intern.presentation.mainmenu.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.internapp.ui.theme.InternAppTheme
import com.intern.presentation.mainmenu.viewModel.MenuViewModel
import kotlinx.serialization.builtins.MapEntrySerializer


@Composable
fun MainMenu(navController: NavHostController, viewModel: MenuViewModel = hiltViewModel()) {

    val isDarkTheme = viewModel.themeModeState.collectAsState(false)

    InternAppTheme(darkTheme = isDarkTheme.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(24.dp)
            ) {
                ThemeSwitcher( isDarkTheme = isDarkTheme.value) {
                    viewModel.saveSettings(it)
                }
                MenuItem(
                    title = "Weather",
                    onClick = { navController.navigate("weather_screen") }
                )
                MenuItem(
                    title ="Stopwatch",
                    onClick = { navController.navigate("stopwatch_screen") }
                )
                MenuItem(
                    title = "News",
                    onClick = {navController.navigate("news_screen")}
                )
            }
        }
    }
    val uiTheme  = MaterialTheme.colorScheme


}

@Composable
fun MenuItem(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun ThemeSwitcher(
    isDarkTheme: Boolean,
    onToggle: (Boolean) -> Unit
) {
    val colors = MaterialTheme.colorScheme
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = if (isDarkTheme) "Dark Theme" else "Light Theme",
            color = colors.onBackground,
            modifier = Modifier.weight(1f)
        )
        androidx.compose.material3.Switch(
            checked = isDarkTheme,
            onCheckedChange = { onToggle(it) }
        )
    }
}




