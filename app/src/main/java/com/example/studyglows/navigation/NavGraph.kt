package com.example.studyglows.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.studyglows.navigation.navgraphs.authNavGraph
import com.example.studyglows.navigation.navgraphs.cartNavGraph
import com.example.studyglows.navigation.navgraphs.dashboardNavGraph
import com.example.studyglows.screens.welcome.WelcomeScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Welcome.route,
        route = Route.ROOT_ROUTE.name
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(
                navHostController = navHostController,
                modifier = Modifier.fillMaxSize()
            )
        }
        authNavGraph(navHostController = navHostController)
        dashboardNavGraph(navHostController = navHostController)
        cartNavGraph(navHostController = navHostController)
    }
}