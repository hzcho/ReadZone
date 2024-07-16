package com.example.readingzone.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.readingzone.presentation.navigation.nav_graph.MainNavGraph
import com.example.readingzone.ui.theme.ReadingZoneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReadingZoneTheme {
                MainNavGraph(navController = rememberNavController())
            }
        }
    }
}