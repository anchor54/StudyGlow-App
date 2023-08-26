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
import com.example.studyglows.screens.testseries.subscreens.AttemptedTestsScreen
import com.example.studyglows.screens.testseries.subscreens.ExploreTestsScreen
import com.example.studyglows.screens.testseries.subscreens.PopularTestsScreen
import com.example.studyglows.screens.testseries.subscreens.SavedTests
import com.example.studyglows.screens.testseries.subscreens.TestDashboard
import com.example.studyglows.screens.testseries.subscreens.TestDetailsScreen
import com.example.studyglows.shared.viewmodels.SharedViewModel

fun NavGraphBuilder.testsNavGraph(
    navHostController: NavHostController,
    appVM: SharedViewModel
) {
    navigation(
        route = Route.TEST_SERIES.name,
        startDestination = Screen.TestDashboard.route
    ) {
        composable(route = Screen.TestDashboard.route) {
            TestDashboard(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController = navHostController),
                sharedViewModel = appVM,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Screen.ExploreTest.route) {
            ExploreTestsScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController = navHostController),
                sharedViewModel = appVM,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            route = Screen.TestDetails.route + "?testId={testId}",
            arguments = listOf(
                navArgument(name = "testId") {
                    type = NavType.StringType
                }
            )
        ) {
            TestDetailsScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController),
                sharedViewModel = appVM,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Screen.TestsAttempted.route) {
            AttemptedTestsScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController),
                sharedViewModel = appVM,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Screen.PopularTests.route) {
            PopularTestsScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController),
                sharedViewModel = appVM,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Screen.SavedTests.route) {
            SavedTests(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController),
                sharedViewModel = appVM,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}