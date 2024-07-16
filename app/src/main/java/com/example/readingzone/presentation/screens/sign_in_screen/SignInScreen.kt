package com.example.readingzone.presentation.screens.sign_in_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.readingzone.R
import com.example.readingzone.presentation.screens.sign_up_screen.SignUpViewModel
import com.example.readingzone.ui.theme.CalibriFontFamily
import com.example.readingzone.utils.UiEvent

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateAndClear:(String)->Unit
) {
    val state by viewModel.signInState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateAndClear -> {
                    navigateAndClear(event.route)
                }
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }
                else -> {}
            }
        }
    }

    SignInBody(
        state = state,
        onEvent = viewModel::onEvent,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignInBody(
    state: SignInState,
    onEvent: (SignInEvent) -> Unit,
    snackbarHost: @Composable () -> Unit
) {
    Scaffold(
        snackbarHost = { snackbarHost() }
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (welcomeImage, emailTF, passwordTF, signUpButton, signInText) = createRefs()

            Image(
                painterResource(R.drawable.welcome),
                contentDescription = "welcome",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .constrainAs(welcomeImage) {
                        top.linkTo(parent.top, margin = 48.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            )

            TextField(
                shape = RoundedCornerShape(8.dp),
                value = state.email,
                onValueChange = { newText ->
                    onEvent(
                        SignInEvent.OnEmailTextChange(email = newText)
                    )
                },
                placeholder = {
                    Text(text = "email")
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.constrainAs(emailTF) {
                    top.linkTo(welcomeImage.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            TextField(
                shape = RoundedCornerShape(8.dp),
                value = state.password,
                onValueChange = { newText ->
                    onEvent(
                        SignInEvent.OnPasswordTextChange(password = newText)
                    )
                },
                placeholder = {
                    Text(text = "password")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        onEvent(SignInEvent.ChangePasswordVisible)
                    }) {
                        Icon(
                            painter = painterResource(
                                if (state.passwordVisible) R.drawable.ic_visible
                                else R.drawable.ic_unvisible
                            ),
                            contentDescription = null
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.constrainAs(passwordTF) {
                    top.linkTo(emailTF.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                },
                visualTransformation = if (state.passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation()
            )

            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    onEvent(SignInEvent.SignIn)
                },
                modifier = Modifier.constrainAs(signUpButton) {
                    bottom.linkTo(parent.bottom, margin = 56.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                }
            ) {
                Text(text = "Sign In")
            }

            Text(
                text = buildAnnotatedString {
                    append("Don't you have an account?")
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue
                        )
                    ) {
                        append(" Sign Up")
                    }
                },
                style = TextStyle(
                    fontFamily = CalibriFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(signInText) {
                    top.linkTo(signUpButton.bottom, margin = 16.dp)
                    start.linkTo(signUpButton.start)
                    end.linkTo(signUpButton.end)
                    width = Dimension.fillToConstraints
                }.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onEvent(SignInEvent.OpenSignUpScreen)
                }
            )
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignInBody(
        state = SignInState(),
        onEvent = {},
        snackbarHost = {}
    )
}