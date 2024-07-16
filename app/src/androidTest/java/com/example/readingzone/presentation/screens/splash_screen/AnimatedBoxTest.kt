package com.example.readingzone.presentation.screens.splash_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AnimatedBoxTest {
    @get:Rule
    val rule = createComposeRule()
    var enabled by mutableStateOf(false)

    @Test
    fun testAnimatedBox() {
        rule.mainClock.autoAdvance=false

        rule.setContent {
            AnimatedBox(
                isStartAnim = enabled,
                onFinished = {}
            )
        }
        enabled=true
        rule.mainClock.advanceTimeBy(500)
    }
}