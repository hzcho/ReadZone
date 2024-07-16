package com.example.readingzone.presentation.screens.home_screen

import com.example.domain.model.BookModel

data class HomeState(
    val books:List<BookModel> = emptyList()
)