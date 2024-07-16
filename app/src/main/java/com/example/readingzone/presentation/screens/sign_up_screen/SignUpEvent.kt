package com.example.readingzone.presentation.screens.sign_up_screen

sealed class SignUpEvent {
    data class OnNameTextChange(val name:String): SignUpEvent()
    data class OnEmailTextChange(val email:String): SignUpEvent()
    data class OnPasswordTextChange(val password:String): SignUpEvent()
    data object SignUp: SignUpEvent()
    data object OpenSignInScreen: SignUpEvent()
    data object ChangePasswordVisible:SignUpEvent()
}