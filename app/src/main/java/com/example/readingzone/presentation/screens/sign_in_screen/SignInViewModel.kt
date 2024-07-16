package com.example.readingzone.presentation.screens.sign_in_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Response
import com.example.domain.parameters.SignInParam
import com.example.domain.usecase.auth.SignInUser
import com.example.readingzone.presentation.navigation.routes.AuthRoutes
import com.example.readingzone.presentation.navigation.routes.BottomNavRoutes
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
class SignInViewModel @Inject constructor(
    private val signInUser: SignInUser
):ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    fun onEvent(event:SignInEvent)=with(_signInState){
        when(event){
            is SignInEvent.ChangePasswordVisible->{
                value=value.copy(
                    passwordVisible = !value.passwordVisible
                )
            }
            is SignInEvent.OnEmailTextChange -> {
                value=value.copy(email = event.email)
            }
            is SignInEvent.OnPasswordTextChange -> {
                value=value.copy(password = event.password)
            }
            SignInEvent.OpenSignUpScreen -> {
                sendUiEvent(UiEvent.NavigateAndClear(route = AuthRoutes.SignUp.route))
            }
            SignInEvent.SignIn -> {
                val email = value.email
                val password = value.password

                if (email.isEmpty() || password.isEmpty()) return@with

                viewModelScope.launch(Dispatchers.IO) {
                    val response=signInUser(
                        param = SignInParam(
                            email = email,
                            password = password
                        )
                    )

                    when(response){
                        is Response.Error -> {
                            sendUiEvent(UiEvent.ShowSnackBar(message = response.error.message ?: "что-то пошло не так"))
                        }
                        is Response.Success -> {
                            if (response.data){
                                sendUiEvent(UiEvent.NavigateAndClear(BottomNavRoutes.Home.route))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}