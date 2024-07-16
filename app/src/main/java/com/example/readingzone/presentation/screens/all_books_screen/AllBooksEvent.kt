package com.example.readingzone.presentation.screens.all_books_screen

sealed class AllBooksEvent {
    data class OnBookClick(val id:String):AllBooksEvent()
    data object OnSwipeToRefresh:AllBooksEvent()
    data object OnReachEnd:AllBooksEvent()
}