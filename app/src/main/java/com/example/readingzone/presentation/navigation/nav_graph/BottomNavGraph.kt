package com.example.readingzone.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.readingzone.presentation.navigation.routes.BottomNavRoutes
import com.example.readingzone.presentation.navigation.routes.NavGraphRoutes
import com.example.readingzone.presentation.screens.bottom_nav_screen.BottomNavScreen
import com.example.readingzone.presentation.screens.home_screen.HomeScreen
import com.example.readingzone.presentation.screens.profile_screen.ProfileScreen
import com.example.readingzone.presentation.screens.search_screen.SearchScreen
import com.example.readingzone.utils.extensions.navigateAndClear
import com.example.readingzone.utils.extensions.navigateSingleTopTo

fun NavGraphBuilder.bottomNavGraph(navController: NavHostController) {
    navigation(
        route = NavGraphRoutes.BottomNavGraph.route,
        startDestination = BottomNavRoutes.Home.route
    ) {
        composable(
            route = BottomNavRoutes.Home.route
        ) {
            BottomNavScreen(
                currentRoute = BottomNavRoutes.Home.route,
                navigateSingle = { route ->
                    navController.navigateSingleTopTo(route, NavGraphRoutes.BottomNavGraph.route)
                }
            ) {
                HomeScreen { route ->
                    navController.navigateSingleTopTo(route, NavGraphRoutes.BottomNavGraph.route)
                }
            }
        }

        composable(
            route=BottomNavRoutes.Search.route
        ){
            BottomNavScreen(
                currentRoute = BottomNavRoutes.Search.route,
                navigateSingle = { route ->
                    navController.navigateSingleTopTo(route, NavGraphRoutes.BottomNavGraph.route)
                }
            ) {
                SearchScreen{route->
                    navController.navigate(route)
                }
            }
        }

        composable(
            route = BottomNavRoutes.Profile.route
        ) {
            BottomNavScreen(
                currentRoute = BottomNavRoutes.Profile.route,
                navigateSingle = { route ->
                    navController.navigateSingleTopTo(route, NavGraphRoutes.BottomNavGraph.route)
                }
            ) { paddingValues ->
                ProfileScreen(
                    paddingValues = paddingValues
                ){route->
                    navController.navigateAndClear(route)
                }
            }
        }
    }
}