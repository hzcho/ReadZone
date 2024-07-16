package com.example.readingzone.presentation.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.domain.model.BookModel
import com.example.readingzone.R
import com.example.readingzone.ui.theme.ReadingZoneTheme

@Composable
fun BookItem(
    book: BookModel,
    onClick: (String) -> Unit = {}
) {
    val imageSize = remember { 120.dp }
    val rating: Float = remember {
        book.run {
            if (likeCount == 0) {
                0f
            } else {
                likeCount.toFloat() / (likeCount + dislikeCount) * 5
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable {
                onClick(book.id)
            }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            val (image, title, author, ratingRow, favoriteButton, readButton) = createRefs()

            AsyncImage(
                model = book.imageUrl,
                contentDescription = null,
                placeholder = painterResource(R.drawable.book),
                error = painterResource(R.drawable.book),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(imageSize)
                    .width(imageSize * 0.75f)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Text(
                text = book.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(title) {
                        start.linkTo(image.end, margin = 16.dp)
                        top.linkTo(parent.top)
                    }
            )

            Text(
                text = book.authorName,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.outline,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(author) {
                        start.linkTo(title.start)
                        top.linkTo(title.bottom)
                    }
            )

            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(ratingRow) {
                        start.linkTo(title.start)
                        top.linkTo(author.bottom, margin = 8.dp)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                for (i in 1..5) {
                    val iconPainter = when {
                        i < rating -> painterResource(R.drawable.ic_star)
                        i - rating in 0.5..0.9 -> painterResource(R.drawable.ic_star_half)
                        else -> painterResource(R.drawable.ic_star_border)
                    }
                    Image(
                        painter = iconPainter,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                }
            }

            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier.constrainAs(favoriteButton) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_favorite_border),
                    contentDescription = null
                )
            }

            OutlinedButton(
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = { /* TODO */ },
                modifier = Modifier.constrainAs(readButton) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Text(
                    text = "Read",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun BookItemPreview() {
    ReadingZoneTheme {
        BookItem(
            book = BookModel(
                id = "Deonte",
                name = "Gianni",
                authorName = "Hugo",
                imageUrl = "",
                description = "Juliana",
                resUrl = "Kenyada",
                format = "txt",
                likeCount = 90,
                dislikeCount = 10
            )
        )
    }
}
