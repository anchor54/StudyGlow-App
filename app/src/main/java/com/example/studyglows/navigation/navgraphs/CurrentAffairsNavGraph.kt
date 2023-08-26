package com.example.studyglows.navigation.navgraphs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.CurrentAffairDetails
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.CurrentAffairs
import com.example.studyglows.shared.viewmodels.SharedViewModel

fun NavGraphBuilder.currentAffairsNavGraph(
    navHostController: NavHostController,
    appVm: SharedViewModel
) {
    navigation(
        startDestination = Screen.CurrentAffairsList.route,
        route = Route.CURRENT_AFFAIRS_ROUTE.name
    ) {
        composable(route = Screen.CurrentAffairsList.route) {
            CurrentAffairs(
                sharedViewModel = appVm,
                viewModel = it.getViewModel(navHostController = navHostController),
                modifier = Modifier.fillMaxSize(),
                navHostController = navHostController
            )
        }
        composable(
            route = Screen.CurrentAffairDetails.route + "/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )
        ) {
            CurrentAffairDetails(
                viewModel = it.getViewModel(navHostController = navHostController),
                modifier = Modifier.fillMaxSize(),
                navHostController = navHostController
            )
        }
    }
}