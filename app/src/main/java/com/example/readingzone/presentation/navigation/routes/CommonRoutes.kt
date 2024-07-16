package com.example.readingzone.presentation.navigation.routes

sealed class CommonRoutes(val route: String) {

    data object Splash : CommonRoutes(route = "splash_screen")
    data object Category:CommonRoutes(route = "category_screen/$CATEGORY_ID={$CATEGORY_ID}"){
        fun passId(categoryId:String)="category_screen/$CATEGORY_ID=$categoryId"
    }
    data object Book : CommonRoutes(route = "book_screen/$BOOK_ID={$BOOK_ID}") {

        fun passId(bookId: String) = "book_screen/$BOOK_ID=$bookId"
    }

    companion object {
        private const val BOOK_ID = "book_id"
        private const val CATEGORY_ID="category_id"
    }
}