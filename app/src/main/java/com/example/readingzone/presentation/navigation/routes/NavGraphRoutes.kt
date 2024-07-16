package com.example.readingzone.presentation.navigation.routes

sealed class NavGraphRoutes(val route:String) {
    data object AuthNavGraph: NavGraphRoutes(route = "auth_nav_graph")
    data object BottomNavGraph:NavGraphRoutes(route = "bottom_nav_graph")
}