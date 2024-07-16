package com.example.readingzone.presentation.screens.categories_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.*
import com.example.domain.parameters.CategoryBookIdsParam
import com.example.domain.parameters.PaginationParam
import com.example.domain.usecase.category.GetBookIdsByCategory
import com.example.domain.usecase.book.GetBooksByIds
import com.example.domain.usecase.category.GetCategories
import com.example.readingzone.presentation.navigation.routes.CommonRoutes
import com.example.readingzone.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategories: GetCategories,
    private val getBooksByIds: GetBooksByIds,
    private val getBookIdsByCategory: GetBookIdsByCategory
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _categoriesState = MutableStateFlow(CategoriesState())
    val categoriesState = _categoriesState.asStateFlow()
    private var page = 0

    init {
        updateCategoriesWithBooks()
    }

    fun onEvent(event: CategoriesEvent) {
        when (event) {
            is CategoriesEvent.OnBookClick -> {
                sendUiEvent(UiEvent.Navigate(CommonRoutes.Book.passId(event.id)))
            }

            is CategoriesEvent.OnCategoryClick -> {
                sendUiEvent(UiEvent.Navigate(CommonRoutes.Category.passId(event.id)))
            }
        }
    }

    private fun updateCategoriesWithBooks(){
        viewModelScope.launch(Dispatchers.IO) {
            val categoriesWithBooks=getCategoriesWithBooks()

            withContext(Dispatchers.Main){
                _categoriesState.apply {
                    value=value.copy(
                        categoriesWithBooks=categoriesWithBooks
                    )
                }
            }
        }
    }

    private suspend fun getCategoriesWithBooks():List<CategoryWithBooks> {
        val categories = getCategoriesResponse()

        val categoriesWithBooks = categories.map { category ->
            val ids = getIdsResponse(category.id)
            val books = getBooksResponse(ids)

            CategoryWithBooks(category = category, books = books)
        }

        return categoriesWithBooks
    }

    private suspend fun getCategoriesResponse(): List<CategoryModel> {
        when (val categories = getCategories(PaginationParam(page = page, limit = 10))) {
            is Response.Error -> {}
            is Response.Success -> {
                return categories.data
            }
        }

        return emptyList()
    }

    private suspend fun getIdsResponse(categoryId: String): List<String> {
        when (val bookIds = getBookIdsByCategory(
            CategoryBookIdsParam(limit = 10, page = 0, categoryId = categoryId)
        )
        ) {
            is Response.Error -> {}
            is Response.Success -> {
                return bookIds.data
            }
        }

        return emptyList()
    }

    private suspend fun getBooksResponse(ids: List<String>): List<BookModel> {
        when (val books = getBooksByIds(ids)) {
            is Response.Error -> {}
            is Response.Success -> {
                return books.data
            }
        }

        return emptyList()
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
