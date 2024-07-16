package com.example.readingzone.presentation.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readingzone.ui.theme.ReadingZoneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLine(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    SearchBar(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = false,
        onActiveChange = { newActive ->
            if(newActive){
                onClick()
            }
        },
        content = {},
        leadingIcon = {
            IconButton(onClick = {

            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = {

                }) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    ReadingZoneTheme {
        SearchLine(
            query = "pop",
            onQueryChange = { },
            onSearch = { }
        )
    }
}