package com.example.readingzone.presentation.screens.all_books_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.usecase.book.GetBooksByPage
import com.example.readingzone.presentation.navigation.routes.CommonRoutes
import com.example.readingzone.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllBooksViewModel @Inject constructor(
    private val getBooksByPage: GetBooksByPage
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _allBooksState = MutableStateFlow(AllBooksState())
    val allBookState = _allBooksState.asStateFlow()
    private var page = 0

    init {
        setBooks()
    }

    fun onEvent(event: AllBooksEvent) = with(_allBooksState) {
        when (event) {
            is AllBooksEvent.OnBookClick -> {
                sendUiEvent(
                    UiEvent.Navigate(
                        CommonRoutes.Book.passId(event.id)
                    )
                )
            }

            is AllBooksEvent.OnReachEnd -> {
                addBooks()
            }

            is AllBooksEvent.OnSwipeToRefresh -> {
                setBooks()
            }
        }
    }

    private fun addBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            _allBooksState.apply {
                val response = getBooksByPage(
                    param = PaginationParam(page = page, limit = 10)
                )

                when (response) {
                    is Response.Error -> {}
                    is Response.Success -> {
                        value = value.copy(
                            books = value.books + response.data
                        )
                    }
                }

                page++
            }
        }
    }

    private fun setBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            _allBooksState.apply {
                value = value.copy(
                    isLoading = true
                )
                val response = getBooksByPage(
                    param = PaginationParam(page = page, limit = 10)
                )

                when (response) {
                    is Response.Error -> {}
                    is Response.Success -> {
                        value = value.copy(
                            books = response.data
                        )
                    }
                }

                page = 0
                value = value.copy(
                    isLoading = false
                )
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}