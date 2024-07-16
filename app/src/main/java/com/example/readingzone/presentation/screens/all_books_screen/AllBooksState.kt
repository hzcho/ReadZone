package com.example.readingzone.presentation.screens.all_books_screen

import com.example.domain.model.BookModel

data class AllBooksState(
    val isLoading:Boolean=false,
    val books:List<BookModel> = emptyList()
)
