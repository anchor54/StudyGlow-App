package com.example.studyglows.screens.testseries.subscreens.testscreens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.navigation.navgraphs.getViewModel
import com.example.studyglows.screens.testseries.TestSeriesViewModel
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun TestScreen(
    modifier: Modifier = Modifier,
    viewModel: TestSeriesViewModel,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    val testNavHost = rememberNavController()
    val testId = navHostController.currentBackStackEntry?.arguments?.getString("testId")

    NavHost(
        navController = testNavHost,
        startDestination = Route.TEST_START.name,
        route = Route.TEST_ROOT.name
    ) {
        navigation(
            route = Route.TEST_START.name,
            startDestination = Screen.TestResults.route + "?testId=$testId"
        ) {
            composable(
                route = Screen.TestWelcome.route + "?testId={testId}",
                arguments = listOf(
                    navArgument(name = "testId") {
                        type = NavType.StringType
                    }
                )
            ) {
                TestWelcome(
                    navHostController = testNavHost,
                    viewModel = it.getViewModel(navHostController = testNavHost),
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(
                route = Screen.TestQuestions.route + "?testId={testId}",
                arguments = listOf(
                    navArgument(name = "testId") {
                        type = NavType.StringType
                    }
                )
            ) {
                TestQuestionsScreen(
                    navHostController = testNavHost,
                    viewModel = it.getViewModel(navHostController = testNavHost),
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(
                route = Screen.TestResults.route + "?testId={testId}",
                arguments = listOf(
                    navArgument(name = "testId") {
                        type = NavType.StringType
                    }
                )
            ) {
                TestResultScreen(
                    navHostController = testNavHost,
                    viewModel = it.getViewModel(navHostController = testNavHost),
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}