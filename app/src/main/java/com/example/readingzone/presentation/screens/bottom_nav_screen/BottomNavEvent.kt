package com.example.readingzone.presentation.screens.bottom_nav_screen

import com.example.readingzone.presentation.navigation.routes.BottomNavRoutes

sealed class BottomNavEvent {
    data class Navigate(val route:String):BottomNavEvent()
}