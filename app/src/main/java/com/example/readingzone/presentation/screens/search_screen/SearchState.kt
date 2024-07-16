package com.example.readingzone.presentation.screens.search_screen

import com.example.domain.model.BookModel

data class SearchState(
    val query:String="",
    val active:Boolean=false,
    val books:List<BookModel> = emptyList()
)
