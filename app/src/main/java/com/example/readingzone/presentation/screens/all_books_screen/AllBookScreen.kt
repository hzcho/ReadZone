package com.example.readingzone.presentation.screens.all_books_screen

import android.util.Log
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
import com.example.domain.model.BookModel
import com.example.readingzone.presentation.elements.BookItem
import com.example.readingzone.ui.theme.ReadingZoneTheme
import com.example.readingzone.utils.UiEvent

@Composable
fun AllBooksScreen(
    viewModel: AllBooksViewModel = hiltViewModel(),
    navigate:(String)->Unit
) {
    val state by viewModel.allBookState.collectAsState()

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

    AllBooksBody(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AllBooksBody(
    state: AllBooksState,
    onEvent: (AllBooksEvent) -> Unit
) {
    val swipeRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            onEvent(AllBooksEvent.OnSwipeToRefresh)
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
                state.books,
                key = {
                    it.id
                }
            ) { item ->
                BookItem(
                    book = item,
                    onClick = { id ->
                        onEvent(AllBooksEvent.OnBookClick(id = id))
                    }
                )
                Spacer(
                    modifier = Modifier.fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.outline)
                )
            }

            item {
                if (state.books.isNotEmpty()) {
                    LaunchedEffect(key1 = null) {
                        onEvent(AllBooksEvent.OnReachEnd)
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
fun AllBooksPreview() {
    ReadingZoneTheme {
        AllBooksBody(
            state = AllBooksState(
                books = listOf(
                    BookModel(
                        id = "popa",
                        name = "Maren",
                        authorName = "Zachary",
                        imageUrl = "https://icdn.lenta.ru/images/2023/03/23/12/20230323123907835/square_320_d3e8288d996e0fc0e3dae6041fd80390.jpg",
                        description = "Liset",
                        resUrl = "Serafin",
                        format = "txt",
                        likeCount = 0,
                        dislikeCount = 0
                    )
                )
            ),
            onEvent = {}
        )
    }
}