package com.example.readingzone.presentation.screens.profile_screen

sealed class ProfileEvent {
    data object SignOut:ProfileEvent()
}