package com.example.readingzone.presentation.screens.splash_screen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readingzone.R
import com.example.readingzone.ui.theme.ReadingZoneTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedBox(
    isStartAnim: Boolean,
    onFinished: () -> Unit
) {
    val transition = updateTransition(targetState = isStartAnim, label = "visibilityTransition")
    val generalTime = 1000

    LaunchedEffect(key1 = transition.currentState) {
        transition.apply {
            if (currentState == targetState && currentState) onFinished()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        transition.AnimatedVisibility(
            visible = { it },
            enter = fadeIn(animationSpec = tween(durationMillis = generalTime))
                    + slideInVertically(animationSpec = tween(durationMillis = generalTime))
        ) {
            Image(
                painter = painterResource(R.drawable.book),
                contentDescription = null,
                modifier = Modifier.size(100.dp).padding()
            )
        }

        transition.AnimatedVisibility(
            visible = { it },
            enter = fadeIn(animationSpec = tween(durationMillis = generalTime))
                    + expandHorizontally(animationSpec = tween(durationMillis = generalTime)),
        ) {
            Text(
                text = "ReadZone",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}

@Preview
@Composable
fun AnimatedBoxPreview() {
    ReadingZoneTheme {
        AnimatedBox(isStartAnim = false, onFinished = {})
    }
}
