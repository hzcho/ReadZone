package com.example.readingzone.presentation.screens.reader_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ReaderMenu(
    modifier: Modifier =Modifier.fillMaxSize()
){
    Box(modifier=modifier){
        Box(modifier=Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)
        ){
            //TODO icons for actions
        }

        Box(modifier=Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
        ){
            //TODO progress bar
        }
    }
}