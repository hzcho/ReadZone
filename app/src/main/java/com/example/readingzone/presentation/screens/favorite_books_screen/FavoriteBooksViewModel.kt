package com.example.readingzone.presentation.screens.favorite_books_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.BookModel
import com.example.domain.parameters.PaginationParam
import com.example.domain.model.Response
import com.example.domain.usecase.book.GetBooksByIds
import com.example.domain.usecase.user.GetFavoriteBookIds
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
class FavoriteBooksViewModel @Inject constructor(
    private val getFavoriteBookIds: GetFavoriteBookIds,
    private val getBooksByIds: GetBooksByIds
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _favoriteBooksState = MutableStateFlow(FavoriteBooksState())
    val favoriteBooksState = _favoriteBooksState.asStateFlow()
    private var page = 0

    fun onEvent(event: FavoriteBooksEvent) {
        when (event) {
            is FavoriteBooksEvent.OnBookClick -> {
                sendUiEvent(UiEvent.Navigate(CommonRoutes.Book.passId(event.id)))
            }

            FavoriteBooksEvent.OnReachEnd -> {
                addBooks()
            }

            FavoriteBooksEvent.OnSwipeToRefresh -> {
                setBooks()
            }
        }
    }

    private fun addBooks(){
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteBooksState.apply {
                value=value.copy(
                    favoriteBooks = value.favoriteBooks+getBooks(page)
                )
            }

            page=0
        }
    }

    private fun setBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteBooksState.apply {
                value = value.copy(
                    isLoading = true
                )
                value=value.copy(
                    favoriteBooks = getBooks(0)
                )
                value = value.copy(
                    isLoading = false
                )
            }

            page=0
        }
    }

    private suspend fun getBooks(page: Int): List<BookModel> {
        val bookIdsResponse = getFavoriteBookIds(
            PaginationParam(
                page = page, limit = 10
            )
        )

        val bookIds = when (bookIdsResponse) {
            is Response.Error -> return emptyList()
            is Response.Success -> bookIdsResponse.data
        }

        val booksResponse = getBooksByIds(bookIds)
        return when (booksResponse) {
            is Response.Error -> emptyList()
            is Response.Success -> booksResponse.data
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}