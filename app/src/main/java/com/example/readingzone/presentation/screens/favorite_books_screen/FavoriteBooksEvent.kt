package com.example.readingzone.presentation.screens.favorite_books_screen

import com.example.readingzone.presentation.screens.all_books_screen.AllBooksEvent

sealed class FavoriteBooksEvent {
    data class OnBookClick(val id:String): FavoriteBooksEvent()
    data object OnSwipeToRefresh: FavoriteBooksEvent()
    data object OnReachEnd: FavoriteBooksEvent()
}