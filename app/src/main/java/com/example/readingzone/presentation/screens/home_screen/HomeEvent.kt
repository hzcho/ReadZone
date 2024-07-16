package com.example.readingzone.presentation.screens.home_screen

sealed class HomeEvent {
    data object OnSearchClick:HomeEvent()
}