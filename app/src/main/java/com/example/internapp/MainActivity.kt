package com.example.internapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.intern.presentation.navigation.NavGraph
import com.example.internapp.ui.theme.InternAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InternAppTheme {
                val navController = rememberNavController()
                NavGraph(navController)

            }
        }

        //val a = Data().add()
    }

}
