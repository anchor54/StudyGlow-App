package com.example.studyglows.screens.test

import android.util.Base64
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.navigation.TestSeriesPathCreator
import com.example.studyglows.navigation.navgraphs.getViewModel
import com.example.studyglows.screens.auth.common.models.TestUIEvent
import com.example.studyglows.screens.test.subscreens.TestQuestionsScreen
import com.example.studyglows.screens.test.subscreens.TestWelcome
import com.example.studyglows.screens.testseries.subscreens.TestResultScreen
import com.example.studyglows.shared.model.NavItem
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun TestFlowContainer(
    viewModel: TestViewModel,
    appVm: SharedViewModel,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val screenParams = appVm.gson.fromJson<List<NavItem>>(
        String(
            Base64.decode(
                navHostController.currentBackStackEntry?.arguments?.getString("screen_params") ?: "",
                Base64.DEFAULT
            ),
            Charsets.UTF_8
        ),
        object : com.google.gson.reflect.TypeToken<List<NavItem>>() {}.type
    )?.toMutableList()

    val screen by remember(screenParams) {
        derivedStateOf {
            if (!screenParams.isNullOrEmpty()) {
                when (screenParams[0].screen) {
                    Screen.TestWelcome.route,
                    Screen.TestQuestions.route -> screenParams[0].screen + "/{testId}"
                    else -> Screen.TestWelcome.route + "/{testId}"
                }
            } else Screen.TestWelcome.route + "/{testId}"
        }
    }
    val params by remember(screenParams) {
        mutableStateOf(if (screenParams.isNullOrEmpty()) listOf() else screenParams[0].params)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is TestUIEvent.OpenTestResultScreen -> {
                    val testId = if (params.isEmpty()) "" else params[0]
                    navHostController.popBackStack()
                    navHostController.navigate(
                        TestSeriesPathCreator().addTestResult(testId).build(appVm.gson)
                    )
                }
            }
        }
    }

    NavHost(
        route = Route.TEST_START.name,
        startDestination = screen,
        navController = navController
    ) {
        composable(
            route = Screen.TestWelcome.route + "/{testId}",
            arguments = listOf(
                navArgument(name = "testId") {
                    type = NavType.StringType
                    defaultValue = if (params.isEmpty()) "" else params[0]
                }
            )
        ) {
            TestWelcome(
                navHostController = navController,
                viewModel = viewModel,
                sharedViewModel = appVm,
                modifier = modifier
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
                navHostController = navController,
                viewModel = viewModel,
                sharedViewModel = appVm,
                modifier = modifier
            )
        }

        composable(
            route = Screen.TestResults.route + "/{testId}",
            arguments = listOf(
                navArgument(name = "testId") {
                    type = NavType.StringType
                    defaultValue = if (params.isEmpty()) "" else params[0]
                }
            )
        ) {
            TestResultScreen(
                navHostController = navController,
                viewModel = it.getViewModel(navHostController = navController),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}