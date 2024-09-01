package com.example.readingzone.presentation.screens.categories_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.BookModel
import com.example.domain.model.CategoryModel
import com.example.readingzone.ui.theme.ReadingZoneTheme
import com.example.readingzone.utils.UiEvent

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel(),
    navigate: (String) -> Unit
) {
    val state by viewModel.categoriesState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate->{
                    navigate(event.route)
                }

                else -> {}
            }
        }
    }

    CategoriesBody(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun CategoriesBody(
    state: CategoriesState,
    onEvent: (CategoriesEvent) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.categoriesWithBooks) { categoryWithBooks ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onEvent(CategoriesEvent.OnCategoryClick(id = categoryWithBooks.category.id))
                        }
                ) {
                    Text(
                        text = categoryWithBooks.category.name,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(categoryWithBooks.books) { book ->
                        CategoryBookItem(name = book.name, imageUrl = book.imageUrl, size = 120.dp) {
                            onEvent(CategoriesEvent.OnBookClick(id = book.id))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoriesScreenPreview() {
    ReadingZoneTheme {
        CategoriesBody(
            state = CategoriesState(
                categoriesWithBooks = listOf(
                    CategoryWithBooks(
                        category = CategoryModel(id = "Timberly", name = "Lukas", rank = 819),
                        books = listOf(
                            BookModel(
                                id = "Jessi",
                                name = "Kymberli",
                                authorName = "Carissa",
                                imageUrl = "Hillel",
                                description = "Jestin",
                                resUrl = "Stephan",
                                format = "Latorya",
                                likeCount = 9355,
                                dislikeCount = 562,
                                categoryIds = emptyList()
                            ),
                            BookModel(
                                id = "Jessi",
                                name = "Kymberli Kymberli Kymberli",
                                authorName = "Carissa",
                                imageUrl = "Hillel",
                                description = "Jestin",
                                resUrl = "Stephan",
                                format = "Latorya",
                                likeCount = 9355,
                                dislikeCount = 562,
                                categoryIds = emptyList()
                            )
                        )
                    )
                )
            ),
            onEvent = {}
        )
    }
}