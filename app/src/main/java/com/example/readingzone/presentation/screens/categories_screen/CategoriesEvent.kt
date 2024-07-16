package com.example.readingzone.presentation.screens.categories_screen

sealed class CategoriesEvent {
    data class OnBookClick(val id:String):CategoriesEvent()
    data class OnCategoryClick(val id:String):CategoriesEvent()
}