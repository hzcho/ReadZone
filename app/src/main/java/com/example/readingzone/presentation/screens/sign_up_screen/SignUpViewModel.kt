package com.example.readingzone.presentation.screens.sign_up_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Response
import com.example.domain.parameters.SignUpParam
import com.example.domain.usecase.auth.SignUpUser
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
class SignUpViewModel @Inject constructor(
    private val signUpUser: SignUpUser
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()

    fun onEvent(event: SignUpEvent) = with(_signUpState) {
        when (event) {
            is SignUpEvent.ChangePasswordVisible->{
                value=value.copy(
                    passwordVisible = !value.passwordVisible
                )
            }
            is SignUpEvent.OnEmailTextChange -> {
                value=value.copy(email = event.email)
            }
            is SignUpEvent.OnNameTextChange -> {
                value=value.copy(name = event.name)
            }
            is SignUpEvent.OnPasswordTextChange -> {
                value=value.copy(password = event.password)
            }
            SignUpEvent.OpenSignInScreen -> {
                sendUiEvent(UiEvent.NavigateAndClear(route = AuthRoutes.SignIn.route))
            }
            SignUpEvent.SignUp -> {
                val name = value.name
                val email = value.email
                val password = value.password

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) return@with

                viewModelScope.launch(Dispatchers.IO) {
                    val response=signUpUser(
                        param = SignUpParam(
                            name = name,
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

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}