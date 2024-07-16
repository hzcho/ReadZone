package com.example.readingzone.presentation.screens.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Response
import com.example.domain.parameters.SearchParam
import com.example.domain.usecase.book.SearchBooks
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
class SearchViewModel @Inject constructor(
    private val searchBooks: SearchBooks
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    fun onEvent(event: SearchEvent) = with(_searchState) {
        when (event) {
            is SearchEvent.OnActiveChange -> {
                value = value.copy(
                    active = event.newActive
                )
            }
            is SearchEvent.OnQueryChange -> {
                value = value.copy(
                    query = event.newQuery
                )
            }
            is SearchEvent.OnSearch -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val lastIndex=if(value.books.isEmpty()) null else value.books.last().id

                    val response = searchBooks(
                        param = SearchParam(
                            limit = 10,
                            lastIndex = lastIndex,
                            query = event.query
                        )
                    )

                    when(response){
                        is Response.Error -> {}
                        is Response.Success ->{
                            value=value.copy(
                                books = response.data
                            )
                        }
                    }
                }
            }
            is SearchEvent.OnBookClick->{
                sendUiEvent(UiEvent.Navigate(event.id))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}