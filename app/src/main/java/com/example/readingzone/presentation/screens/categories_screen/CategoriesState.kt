package com.example.readingzone.presentation.screens.categories_screen

import com.example.domain.model.BookModel
import com.example.domain.model.CategoryModel

data class CategoriesState(
    val categoriesWithBooks: List<CategoryWithBooks> = emptyList()
)

data class CategoryWithBooks(
    val category:CategoryModel,
    val books:List<BookModel>
)