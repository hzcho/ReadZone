package com.example.readingzone.presentation.screens.bottom_nav_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.readingzone.presentation.navigation.routes.BottomNavRoutes
import com.example.readingzone.utils.UiEvent
import com.example.readingzone.utils.extensions.navigateSingleTopTo

@Composable
fun BottomNavScreen(
    currentRoute:String,
    navigateSingle:(String)->Unit,
    viewModel: BottomNavViewModel= hiltViewModel(),
    content: @Composable (PaddingValues) -> Unit
) {
    LaunchedEffect(key1 = viewModel){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.NavigateSingleTopTo -> {
                    navigateSingle(event.route)
                }
                else->{}
            }
        }
    }

    BottomNavBody(
        bottomBar = {
            BottomNav(
                currentRoute=currentRoute,
                navigate = {route->
                    viewModel.onEvent(BottomNavEvent.Navigate(route))
                }
            )
        },
        content = content
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavBody(
    bottomBar:@Composable ()->Unit,
    content: @Composable (PaddingValues) -> Unit
){
    Scaffold(
        bottomBar = {
            bottomBar()
        }
    ) {paddingValues->
        content(paddingValues)
    }
}

@Preview
@Composable
fun BottomNavScreenPreview(){
    BottomNavBody(
        bottomBar = {
            BottomNav(currentRoute = BottomNavRoutes.Home.route, navigate = {})
        },
        content = {
            Box(modifier = Modifier.fillMaxSize().background(Color.Blue))
        }
    )
}