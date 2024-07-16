package com.example.readingzone.presentation.screens.bottom_nav_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readingzone.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class BottomNavViewModel:ViewModel() {
    private val _uiEvent= Channel<UiEvent>()
    val uiEvent=_uiEvent.receiveAsFlow()

    fun onEvent(event:BottomNavEvent){
        when(event){
            is BottomNavEvent.Navigate -> {
                sendUiEvent(UiEvent.NavigateSingleTopTo(route = event.route))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}