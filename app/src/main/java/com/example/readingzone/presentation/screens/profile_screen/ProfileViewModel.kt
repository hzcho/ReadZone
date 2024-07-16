package com.example.readingzone.presentation.screens.profile_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Response
import com.example.domain.usecase.user.GetUser
import com.example.domain.usecase.auth.SignOutUser
import com.example.readingzone.presentation.navigation.routes.NavGraphRoutes
import com.example.readingzone.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val signOutUser: SignOutUser,
    private val getUser: GetUser
):ViewModel() {
    private val _uiEvent= Channel<UiEvent>()
    val uiEvent=_uiEvent.receiveAsFlow()
    private val _profileState = MutableStateFlow(ProfileState())
    val profileState = _profileState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response=getUser(Unit)
            when(response){
                is Response.Error -> {}
                is Response.Success -> {
                    _profileState.apply {
                        value=value.copy(
                            name = response.data.name
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.SignOut -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val response=signOutUser(Unit)

                    when(response){
                        is Response.Error -> {}
                        is Response.Success -> {
                            sendUiEvent(UiEvent.NavigateAndClear(NavGraphRoutes.AuthNavGraph.route))
                        }
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event:UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}