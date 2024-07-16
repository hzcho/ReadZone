package com.example.readingzone.presentation.screens.bottom_nav_screen

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.channels.ticker

@Composable
fun BottomNav(currentRoute: String?, navigate: (String) -> Unit) {
    val items = listOf(
        BottomItem.HomeItem,
        BottomItem.SearchItem,
        BottomItem.ReaderItem,
        BottomItem.PersonBooksItem,
        BottomItem.ProfileItem
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = item.route == currentRoute,
                onClick = {
                    navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(item.iconId),
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = item.title,
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                },
                alwaysShowLabel = false,
                selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                unselectedContentColor = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }
}