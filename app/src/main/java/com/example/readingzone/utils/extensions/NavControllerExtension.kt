package com.example.readingzone.utils.extensions

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.navigateSingleTopTo(route: String, popUpToRoute: String) {
    navigate(route) {
        popUpTo(popUpToRoute) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateAndClear(route: String) {
    navigate(route) {
        previousBackStackEntry?.destination?.route?.let { previousRoute ->
            popUpTo(previousRoute) { inclusive = true }
        }
    }
}