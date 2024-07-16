package com.example.readingzone.presentation.navigation.routes

sealed class BottomNavRoutes(val route:String) {
    data object Home:BottomNavRoutes(route="home_screen")
    data object Search:BottomNavRoutes(route = "search_screen")
    data object Reader:BottomNavRoutes(route = "reader_screen")
    data object PersonBooks:BottomNavRoutes(route = "person_books_screen")
    data object Profile:BottomNavRoutes(route = "profile_screen")
}