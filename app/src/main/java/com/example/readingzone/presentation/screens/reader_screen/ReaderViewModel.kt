package com.example.readingzone.presentation.screens.reader_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.parameters.ReadParam
import com.example.domain.usecase.saved_books.GetSavedBookRes
import com.example.domain.usecase.saved_books.Read
import com.example.readingzone.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class ReaderViewModel @Inject constructor(
    private val readUseCase: Read,
    private val getResUseCase: GetSavedBookRes
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _readerState = MutableStateFlow(ReaderState())
    val readerState = _readerState
    private var page = 0
    private val limit = 100 //TODO realise
    private var res: InputStream? = null

    fun onEvent(event: ReaderEvent)= with(_readerState) {
        when (event) {
            ReaderEvent.Back -> {
                res?.let { r ->
                    viewModelScope.launch(Dispatchers.IO) {
                        page--
                        readUseCase(ReadParam(page = page, charCount = limit, res = r))
                    }
                }
            }

            ReaderEvent.ChangeMenuVisible -> {
                value=value.copy(
                    menuVisible = !value.menuVisible
                )
            }

            ReaderEvent.Next -> {
                res?.let { r ->
                    viewModelScope.launch(Dispatchers.IO) {
                        page++
                        readUseCase(ReadParam(page = page, charCount = limit, res = r))
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    override fun onCleared() {
        super.onCleared()
        res?.close()
    }
}