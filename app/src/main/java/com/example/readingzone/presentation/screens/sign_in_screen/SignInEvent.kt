package com.example.readingzone.presentation.screens.sign_in_screen

import com.example.readingzone.presentation.screens.sign_up_screen.SignUpEvent

sealed class SignInEvent {
    data class OnEmailTextChange(val email:String): SignInEvent()
    data class OnPasswordTextChange(val password:String): SignInEvent()
    data object SignIn: SignInEvent()
    data object OpenSignUpScreen: SignInEvent()
    data object ChangePasswordVisible:SignInEvent()
}