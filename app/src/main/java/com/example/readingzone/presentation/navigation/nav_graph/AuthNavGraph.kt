package com.example.readingzone.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.readingzone.presentation.navigation.routes.AuthRoutes
import com.example.readingzone.presentation.navigation.routes.CommonRoutes
import com.example.readingzone.presentation.navigation.routes.NavGraphRoutes
import com.example.readingzone.presentation.screens.sign_in_screen.SignInScreen
import com.example.readingzone.presentation.screens.sign_up_screen.SignUpScreen
import com.example.readingzone.utils.extensions.navigateAndClear
import com.example.readingzone.utils.extensions.navigateSingleTopTo

fun NavGraphBuilder.authNavGraph(navController:NavHostController) {
    navigation(
        route = NavGraphRoutes.AuthNavGraph.route,
        startDestination = AuthRoutes.SignUp.route
    ) {
        composable(
            route = AuthRoutes.SignUp.route
        ){
            SignUpScreen{ route ->
                navController.navigateAndClear(route)
            }
        }
        composable(
            route = AuthRoutes.SignIn.route
        ){
            SignInScreen{ route ->
                navController.navigateAndClear(route)
            }
        }
    }
}