package com.example.readingzone.presentation.screens.search_screen

sealed class SearchEvent {
    data class OnSearch(val query:String):SearchEvent()
    data class OnQueryChange(val newQuery:String):SearchEvent()
    data class OnActiveChange(val newActive:Boolean):SearchEvent()
    data class OnBookClick(val id:String):SearchEvent()
}