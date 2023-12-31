package com.example.studyglows.navigation.navgraphs

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.example.studyglows.navigation.DeepLink
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.editorial_currentaffair.editorial.Editorials
import com.example.studyglows.screens.editorial_currentaffair.editorial.component.EditorialDetails
import com.example.studyglows.shared.viewmodels.SharedViewModel

fun NavGraphBuilder.editorialNavGraph(
    navHostController: NavHostController,
    appVM: SharedViewModel
) {
    navigation(
        startDestination = Screen.EditorialsList.route,
        route = Route.EDITORIAL_ROUTE.name
    ) {
        composable(route = Screen.EditorialsList.route) {
            Editorials(
                navHostController = navHostController,
                sharedVM = appVM,
                viewModel = it.getViewModel(navHostController = navHostController),
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            route = "${Screen.EditorialDetails.route}/{editorialId}",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = DeepLink.EditorialDetail
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument(name = "editorialId") {
                    type = NavType.StringType
                }
            )
        ) {
            EditorialDetails(
                navHostController = navHostController,
                sharedVM = appVM,
                viewModel = it.getViewModel(navHostController = navHostController),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}