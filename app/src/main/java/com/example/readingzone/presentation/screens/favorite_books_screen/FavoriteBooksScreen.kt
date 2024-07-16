package com.example.readingzone.presentation.screens.favorite_books_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.readingzone.presentation.elements.BookItem
import com.example.readingzone.presentation.screens.all_books_screen.AllBooksEvent
import com.example.readingzone.ui.theme.ReadingZoneTheme
import com.example.readingzone.utils.UiEvent

@Composable
fun FavoriteBooksScreen(
    viewModel: FavoriteBooksViewModel = hiltViewModel(),
    navigateSingle: (String) -> Unit
) {
    val state by viewModel.favoriteBooksState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> navigateSingle(event.route)
                else -> {}
            }
        }
    }

    FavoriteBooksBody(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteBooksBody(
    state: FavoriteBooksState,
    onEvent: (FavoriteBooksEvent) -> Unit
) {
    val swipeRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            onEvent(FavoriteBooksEvent.OnSwipeToRefresh)
        }
    )
    val lazyColumnState = rememberSaveable(saver = LazyListState.Saver) {
        LazyListState()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(swipeRefreshState)
    ) {
        LazyColumn(
            state = lazyColumnState,
            contentPadding = PaddingValues(bottom = 300.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                state.favoriteBooks,
                key = {
                    it.id
                }
            ) { item ->
                BookItem(
                    book = item,
                    onClick = { id ->
                        onEvent(FavoriteBooksEvent.OnBookClick(id = id))
                    }
                )
                Spacer(
                    modifier = Modifier.fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.outline)
                )
            }

            item {
                if (state.favoriteBooks.isNotEmpty()) {
                    LaunchedEffect(key1 = null) {
                        onEvent(FavoriteBooksEvent.OnReachEnd)
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = swipeRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
fun FavoriteBooksScreenPreview() {
    ReadingZoneTheme {
        FavoriteBooksBody(
            state = FavoriteBooksState(),
            onEvent = {}
        )
    }
}