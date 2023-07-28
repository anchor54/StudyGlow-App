package com.example.studyglows.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.components.LoginButton

@Composable
fun WelcomeScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .background(color = Color(0xFFE6F1F8))
    ) {
        val (
            bgWave,
            avatar,
            message,
            loginBtn
        ) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.welcome_wave_bg),
            contentDescription = "background waves",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .constrainAs(bgWave) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.welcome_illustration),
            contentDescription = "boy avatar",
            modifier = Modifier.constrainAs(avatar) {
                bottom.linkTo(bgWave.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Image(
            painter = painterResource(id = R.drawable.welcome_message),
            contentDescription = "welcome message",
            modifier = Modifier.constrainAs(message) {
                top.linkTo(avatar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        LoginButton(
            buttonText = "LOG IN",
            backgroundColor = Color(0xFFE6F1F8),
            onClick = {
                navHostController.navigate(Route.AUTHENTICATION_ROUTE.name)
            },
            modifier = Modifier
                .constrainAs(loginBtn) {
                    top.linkTo(message.bottom, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(0.6f),
        )
    }
}