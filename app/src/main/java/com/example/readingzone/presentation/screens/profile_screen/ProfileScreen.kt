package com.example.readingzone.presentation.screens.profile_screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.readingzone.ui.theme.ReadingZoneTheme
import com.example.readingzone.utils.UiEvent
import com.example.readingzone.R
import com.example.readingzone.utils.extensions.navigateSingleTopTo
import dagger.hilt.android.components.ActivityComponent

@Composable
fun ProfileScreen(
    paddingValues:PaddingValues,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateAndClear:(String)->Unit
) {
    val state by viewModel.profileState.collectAsState()
    val bottomPadding= remember { paddingValues.calculateBottomPadding() }

    LaunchedEffect(key1 = viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateAndClear->{
                    navigateAndClear(event.route)
                }

                else -> {}
            }
        }
    }

    ProfileBody(
        state = state,
        bottomPadding = bottomPadding,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ProfileBody(
    state: ProfileState,
    bottomPadding: Dp =0.dp,
    onEvent: (ProfileEvent) -> Unit
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = bottomPadding)
        .background(MaterialTheme.colorScheme.surface)
    ) {
        val (imageBox, nameText, signOutButton) = createRefs()

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.constrainAs(imageBox) {
                top.linkTo(parent.top, margin = 32.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onSurface)
            )

            Image(
                painter = painterResource(R.drawable.profile_default),
                contentDescription = "profile image",
                modifier = Modifier.size(128.dp).clip(CircleShape)
            )
        }

        Text(text = state.name,
            modifier = Modifier.constrainAs(nameText) {
                top.linkTo(imageBox.bottom, margin = 16.dp)
                start.linkTo(imageBox.start)
                end.linkTo(imageBox.end)
            },
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface
            )
        )


        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = {
                onEvent(ProfileEvent.SignOut)
            },
            modifier = Modifier.constrainAs(signOutButton) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)

                width = Dimension.fillToConstraints
            }
        ) {
            Text(text = "sign out")
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ReadingZoneTheme(darkTheme = true) {
        ProfileBody(
            state = ProfileState(name = "popa"),
            onEvent = {}
        )
    }
}