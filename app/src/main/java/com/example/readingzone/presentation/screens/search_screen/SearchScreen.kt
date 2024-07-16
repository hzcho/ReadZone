package com.example.readingzone.presentation.screens.search_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.readingzone.presentation.elements.BookItem
import com.example.readingzone.presentation.elements.SearchLine
import com.example.readingzone.ui.theme.ReadingZoneTheme
import com.example.readingzone.utils.UiEvent

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigate:(String)->Unit
) {
    val state by viewModel.searchState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navigate(event.route)
                }
                else -> {}
            }
        }
    }

    SearchBody(
        state=state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun SearchBody(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchLine(
            query = state.query,
            onQueryChange = { newQuery ->
                onEvent(SearchEvent.OnQueryChange(newQuery))
            },
            onSearch = { query ->
                onEvent(SearchEvent.OnSearch(query))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.books){book->
                BookItem(book)
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview(){
    ReadingZoneTheme {
        SearchBody(
            state = SearchState(),
            onEvent = {}
        )
    }
}