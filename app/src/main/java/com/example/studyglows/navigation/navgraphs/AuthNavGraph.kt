package com.example.studyglows.navigation.navgraphs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.AuthViewModel
import com.example.studyglows.screens.auth.login.LoginScreen
import com.example.studyglows.screens.auth.otp.OTPScreen
import com.example.studyglows.screens.welcome.WelcomeScreen

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController,
    viewModel: AuthViewModel? = null,
) {
    navigation(
        startDestination = Screen.Login.route,
        route = Route.AUTHENTICATION_ROUTE.name
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                navHostController = navHostController,
                viewModel = viewModel ?: it.getViewModel(navHostController),
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Screen.Otp.route) {
            OTPScreen(
                navHostController = navHostController,
                viewModel = viewModel ?: it.getViewModel(navHostController),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}