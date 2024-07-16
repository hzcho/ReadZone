package com.example.readingzone.presentation.screens.sign_up_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
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
import com.example.readingzone.ui.theme.CalibriFontFamily
import com.example.readingzone.ui.theme.ReadingZoneTheme
import com.example.readingzone.utils.UiEvent

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateAndClear:(String)->Unit
) {
    val state by viewModel.signUpState.collectAsState()
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

    SignUpBody(
        state = state,
        onEvent = viewModel::onEvent,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpBody(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    snackbarHost: @Composable () -> Unit
) {
    Scaffold(
        snackbarHost = { snackbarHost() }
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (welcomeImage, nameTF, emailTF, passwordTF, signUpButton, signInText) = createRefs()

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
                value = state.name,
                onValueChange = { newText ->
                    onEvent(
                        SignUpEvent.OnNameTextChange(name = newText)
                    )
                },
                placeholder = {
                    Text(text = "name")
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "person"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.constrainAs(nameTF) {
                    top.linkTo(welcomeImage.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                }
            )

            TextField(
                shape = RoundedCornerShape(8.dp),
                value = state.email,
                onValueChange = { newText ->
                    onEvent(
                        SignUpEvent.OnEmailTextChange(email = newText)
                    )
                },
                placeholder = {
                    Text(text = "email")
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = "email"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.constrainAs(emailTF) {
                    top.linkTo(nameTF.bottom, margin = 20.dp)
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
                        SignUpEvent.OnPasswordTextChange(password = newText)
                    )
                },
                placeholder = {
                    Text(text = "password")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        onEvent(SignUpEvent.ChangePasswordVisible)
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
                    onEvent(SignUpEvent.SignUp)
                },
                modifier = Modifier.constrainAs(signUpButton) {
                    bottom.linkTo(parent.bottom, margin = 56.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                }
            ) {
                Text(text = "Sign Up")
            }

            Text(
                text = buildAnnotatedString {
                    append("do you already have an account?")
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue
                        )
                    ) {
                        append(" Sign In")
                    }
                },
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = CalibriFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.constrainAs(signInText) {
                    top.linkTo(signUpButton.bottom, margin = 16.dp)
                    start.linkTo(signUpButton.start)
                    end.linkTo(signUpButton.end)
                    width = Dimension.fillToConstraints
                }.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onEvent(SignUpEvent.OpenSignInScreen)
                }
            )
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    ReadingZoneTheme {
        SignUpBody(
            state = SignUpState(),
            onEvent = {},
            snackbarHost = {}
        )
    }
}