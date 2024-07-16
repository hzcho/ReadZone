package com.example.readingzone.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.readingzone.presentation.navigation.routes.CommonRoutes
import com.example.readingzone.presentation.navigation.routes.NavGraphRoutes
import com.example.readingzone.presentation.screens.splash_screen.SplashScreen
import com.example.readingzone.utils.extensions.navigateAndClear
import com.example.readingzone.utils.extensions.navigateSingleTopTo

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = CommonRoutes.Splash.route
    ) {
        authNavGraph(navController)
        bottomNavGraph(navController)
        composable(
            route = CommonRoutes.Splash.route
        ){
            SplashScreen{route->
                navController.navigateAndClear(route)
            }
        }
    }
}