package com.example.readingzone.presentation.screens.reader_screen

sealed class ReaderEvent {
    data object Next:ReaderEvent()
    data object Back:ReaderEvent()
    data object ChangeMenuVisible:ReaderEvent()
}