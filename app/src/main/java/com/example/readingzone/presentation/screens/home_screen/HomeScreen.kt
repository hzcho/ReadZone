package com.example.readingzone.presentation.screens.home_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.readingzone.presentation.elements.SearchLine
import com.example.readingzone.presentation.elements.TabLayout
import com.example.readingzone.presentation.screens.all_books_screen.AllBooksScreen
import com.example.readingzone.presentation.screens.categories_screen.CategoriesScreen
import com.example.readingzone.presentation.screens.favorite_books_screen.FavoriteBooksScreen
import com.example.readingzone.ui.theme.ReadingZoneTheme
import com.example.readingzone.utils.UiEvent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateSingle: (String) -> Unit
) {
    val state by viewModel.homeState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateSingleTopTo -> {
                    navigateSingle(event.route)
                }

                else -> {}
            }
        }
    }

    HomeBody(
        state = state,
        onEvent = viewModel::onEvent
    ) { index ->
        when (index) {
            0 -> {
                AllBooksScreen { route ->
                    navigateSingle(route)
                }
            }

            1 -> {
                CategoriesScreen { route ->
                    navigateSingle(route)
                }
            }

            2 -> {
                FavoriteBooksScreen { route ->
                    navigateSingle(route)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeBody(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    content: @Composable (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchLine(
            query = "",
            onQueryChange = { },
            onSearch = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(start = 8.dp, end = 8.dp)
        ){
            onEvent(HomeEvent.OnSearchClick)
        }

        TabLayout(
            tabs = listOf("книги", "жанры", "избранное"),
            modifier = Modifier.fillMaxWidth()
        ) { index ->
            content(index)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    ReadingZoneTheme {
        HomeBody(
            state = HomeState(),
            onEvent = {}
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
}