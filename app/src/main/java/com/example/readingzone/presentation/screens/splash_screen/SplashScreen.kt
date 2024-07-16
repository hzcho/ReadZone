package com.example.readingzone.presentation.screens.splash_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.readingzone.utils.UiEvent

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateAndClear:(String)->Unit
) {
    val state by viewModel.splashState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.onEvent(SplashEvent.StartAnim)

        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateAndClear -> {
                    navigateAndClear(event.route)
                }
                else -> {}
            }
        }
    }

    SplashBody(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashBody(
    state: SplashState,
    onEvent: (SplashEvent) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AnimatedBox(
            isStartAnim = state.isStartAnim
        ) {
            onEvent(SplashEvent.OnFinishedAnim)
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashBody(
        state = SplashState(isStartAnim = false),
        onEvent = {}
    )
}