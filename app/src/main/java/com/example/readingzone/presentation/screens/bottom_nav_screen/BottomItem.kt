package com.example.readingzone.presentation.screens.bottom_nav_screen

import com.example.readingzone.R
import com.example.readingzone.presentation.navigation.routes.BottomNavRoutes

sealed class BottomItem(val title:String, val iconId:Int, val route:String) {
    data object HomeItem:BottomItem(title ="home", iconId = R.drawable.ic_home, route =BottomNavRoutes.Home.route)
    data object SearchItem:BottomItem(title ="search", iconId = R.drawable.ic_search, route =BottomNavRoutes.Search.route)
    data object ReaderItem:BottomItem(title ="reader", iconId = R.drawable.ic_play, route =BottomNavRoutes.Reader.route)
    data object PersonBooksItem:BottomItem(title ="home", iconId = R.drawable.ic_book, route =BottomNavRoutes.PersonBooks.route)
    data object ProfileItem:BottomItem(title ="home", iconId = R.drawable.ic_person, route =BottomNavRoutes.Profile.route)
}