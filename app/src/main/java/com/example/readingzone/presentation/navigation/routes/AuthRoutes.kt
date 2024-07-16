package com.example.readingzone.presentation.navigation.routes

sealed class AuthRoutes(val route:String) {
    data object SignIn: AuthRoutes(route = "sign_in_screen")
    data object SignUp: AuthRoutes(route = "sign_up_screen")
}