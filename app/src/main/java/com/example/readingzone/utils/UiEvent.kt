package com.example.readingzone.utils

sealed class UiEvent {
    data class NavigateSingleTopTo(val route:String):UiEvent()
    data class NavigateAndClear(val route:String):UiEvent()
    data class Navigate(val route:String):UiEvent()
    data class ShowSnackBar(val message:String):UiEvent()
}