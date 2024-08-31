package com.example.readingzone.presentation.screens.reader_screen

import androidx.compose.foundation.layout.*
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

@Composable
fun ReaderScreen(
    viewModel: ReaderViewModel = hiltViewModel()
) {
    val state by viewModel.readerState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                else -> {}
            }
        }
    }

    ReaderBody(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ReaderBody(
    state: ReaderState,
    onEvent: (ReaderEvent) -> Unit
) {
    Text(
        text = state.pageText,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxHeight().weight(1f)
        ){
            onEvent(ReaderEvent.Back)
        }
        Box(
            modifier = Modifier.fillMaxHeight().weight(2f)
        ){
            onEvent(ReaderEvent.ChangeMenuVisible)
        }
        Box(
            modifier = Modifier.fillMaxHeight().weight(1f)
        ){
            onEvent(ReaderEvent.Next)
        }
    }

    if (state.menuVisible) {
        ReaderMenu(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
@Preview
fun ReaderScreenPreview() {
    ReaderBody(
        state = ReaderState(),
        onEvent = {}
    )
}