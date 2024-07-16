package com.example.readingzone.presentation.screens.favorite_books_screen

import com.example.domain.model.BookModel

data class FavoriteBooksState(
    val isLoading:Boolean=false,
    val favoriteBooks:List<BookModel> = emptyList()
)
