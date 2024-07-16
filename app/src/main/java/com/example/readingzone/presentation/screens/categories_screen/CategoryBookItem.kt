package com.example.readingzone.presentation.screens.categories_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.model.BookModel
import com.example.readingzone.ui.theme.ReadingZoneTheme
import com.example.readingzone.R

@Composable
fun CategoryBookItem(
    name: String,
    imageUrl: String,
    size: Dp = 150.dp,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.5f
            )
        ),
        modifier = Modifier
            .size(size, size * 1.1f)
            .clickable {
                onClick()
            }
    ) {
        Box(
            //contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = imageUrl,
                placeholder = painterResource(R.drawable.book),
                error = painterResource(R.drawable.book),
                contentDescription = null,
                modifier = Modifier.size(size / 2).align(Alignment.Center)
            )

            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp
                    )
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CategoryBookItemPreview() {
    ReadingZoneTheme {
        CategoryBookItem(
            name = "Genevieve Genevieve Genevieve",
            imageUrl = "Griselda",
            onClick = {

            }
        )
    }
}