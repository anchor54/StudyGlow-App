package com.example.studyglows.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.studyglows.screens.auth.login.LoginScreen
import com.example.studyglows.screens.auth.otp.OTPScreen
import com.example.studyglows.screens.welcome.WelcomeScreen

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Screen.Welcome.route,
        route = Route.AUTHENTICATION_ROUTE.name
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navHostController = navHostController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController)
            )
        }
        composable(route = Screen.Otp.route) {
            OTPScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController)
            )
        }
    }
}