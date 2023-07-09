package com.example.studyglows.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.AUTHENTICATION_ROUTE.name,
        route = Route.ROOT_ROUTE.name
    ) {
        authNavGraph(navHostController = navHostController)
    }
}