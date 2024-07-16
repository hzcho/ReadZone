package com.example.readingzone.presentation.screens.splash_screen

sealed class SplashEvent {
    data object StartAnim: SplashEvent()
    data object OnFinishedAnim: SplashEvent()
}