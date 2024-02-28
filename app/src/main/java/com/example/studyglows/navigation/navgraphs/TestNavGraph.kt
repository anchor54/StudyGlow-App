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
import com.example.studyglows.screens.test.subscreens.TestQuestionsScreen
import com.example.studyglows.screens.test.subscreens.TestWelcome
import com.example.studyglows.shared.viewmodels.SharedViewModel

fun NavGraphBuilder.testNavGraph(
    navHostController: NavHostController,
    appVm: SharedViewModel
) {
    navigation(
        route = Route.TEST_START.name,
        startDestination = Screen.TestWelcome.route + "/{testId}"
    ) {
        composable(
            route = Screen.TestWelcome.route + "/{testId}",
            arguments = listOf(
                navArgument(name = "testId") {
                    type = NavType.StringType
                }
            )
        ) {
            TestWelcome(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController = navHostController),
                sharedViewModel = appVm,
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(
            route = Screen.TestQuestions.route + "/{testId}",
            arguments = listOf(
                navArgument(name = "testId") {
                    type = NavType.StringType
                }
            )
        ) {
            TestQuestionsScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController = navHostController),
                sharedViewModel = appVm,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}