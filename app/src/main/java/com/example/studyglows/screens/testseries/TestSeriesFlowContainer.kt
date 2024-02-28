package com.example.studyglows.screens.testseries

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
import com.example.studyglows.navigation.TestPathCreator
import com.example.studyglows.navigation.navgraphs.getViewModel
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.auth.common.models.TestSeriesUIEvent
import com.example.studyglows.screens.testseries.model.testNavDrawerContent
import com.example.studyglows.screens.testseries.subscreens.AttemptedTestsScreen
import com.example.studyglows.screens.testseries.subscreens.ExploreTestsScreen
import com.example.studyglows.screens.testseries.subscreens.PopularTestsScreen
import com.example.studyglows.screens.testseries.subscreens.SavedTests
import com.example.studyglows.screens.testseries.subscreens.TestDashboard
import com.example.studyglows.screens.testseries.subscreens.TestDetailsScreen
import com.example.studyglows.screens.testseries.subscreens.TestResultScreen
import com.example.studyglows.screens.testseries.viewmodel.TestSeriesViewModel
import com.example.studyglows.shared.components.BaseScreenLayout
import com.example.studyglows.shared.components.drawermenu.BaseDrawerNavigation
import com.example.studyglows.shared.model.NavItem
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.google.gson.reflect.TypeToken

@Composable
fun TestSeriesFlowContainer(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    viewModel: TestSeriesViewModel,
    navHostController: NavHostController
) {
    val searchResults by viewModel.searchResult.collectAsState()
    val screenParams = sharedViewModel.gson.fromJson<List<NavItem>>(
        String(
            Base64.decode(
                navHostController.currentBackStackEntry?.arguments?.getString("screen_params") ?: "",
                Base64.DEFAULT
            ),
            Charsets.UTF_8
        ),
        object : TypeToken<List<NavItem>>() {}.type
    )?.toMutableList()
    val screen by remember(screenParams) {
        derivedStateOf {
            if (!screenParams.isNullOrEmpty()) {
                when (screenParams[0].screen) {
                    Screen.TestSeriesDashboard.route,
                    Screen.ExploreTest.route,
                    Screen.TestsAttempted.route,
                    Screen.PopularTests.route,
                    Screen.SavedTests.route -> screenParams[0].screen
                    Screen.TestSeriesDetails.route,
                    Screen.TestResults.route -> screenParams[0].screen + "/{testId}"
                    else -> Screen.TestSeriesDashboard.route
                }
            } else Screen.TestSeriesDashboard.route
        }
    }
    val params by remember(screenParams) {
        mutableStateOf(if (screenParams.isNullOrEmpty()) listOf() else screenParams[0].params)
    }
    val nestedNavController = rememberNavController()

    LaunchedEffect(key1 = Unit) {
        sharedViewModel.setDrawerMidOptions(
            options = testNavDrawerContent(),
            handler = object : BaseDrawerNavigation(navHostController) {
                override fun handleDrawerNavigation(itemId: String) {
                    when (itemId) {
                        "my_tests" -> { nestedNavController.navigate(Screen.TestSeriesDashboard.route) }
                        "explore_tests" -> { nestedNavController.navigate(Screen.ExploreTest.route) }
                        "evaluation" -> { nestedNavController.navigate(Screen.TestsAttempted.route) }
                        "saved_tests" -> { nestedNavController.navigate(Screen.SavedTests.route) }
                        else -> super.handleDrawerNavigation(itemId)
                    }
                }
            }
        )
        viewModel.uiEvent.collect {
            when(it) {
                is TestSeriesUIEvent.OpenTestScreen -> {
                    navHostController.navigate(TestPathCreator().addTestWelcome(it.testId).build(sharedViewModel.gson))
                }
                else -> {}
            }
        }
    }

    BaseScreenLayout(
        openDrawer = { sharedViewModel.sendUIEvent(AppUIEvent.ShowDrawer) },
        modifier = modifier,
        searchResult = searchResults,
        onSearch = { viewModel.getSearchResults(it) },
        onSearchClicked = {},
        onResultItemClicked = { nestedNavController.navigate("${Screen.TestSeriesDetails.route}/$it") }
    ) {
        NavHost(
            navController = nestedNavController,
            startDestination = screen,
            route = Route.TEST_SERIES_ROOT.name
        ) {
            composable(route = Screen.TestSeriesDashboard.route) {
                TestDashboard(
                    navHostController = nestedNavController,
                    viewModel = viewModel,
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = Screen.ExploreTest.route) {
                ExploreTestsScreen(
                    navHostController = nestedNavController,
                    viewModel = viewModel,
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(
                route = Screen.TestSeriesDetails.route + "/{testId}",
                arguments = listOf(
                    navArgument(name = "testId") {
                        type = NavType.StringType
                        defaultValue = if (params.isEmpty()) "" else params[0]
                    }
                )
            ) {
                TestDetailsScreen(
                    navHostController = nestedNavController,
                    viewModel = viewModel,
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier.fillMaxSize()
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
                    navHostController = nestedNavController,
                    viewModel = it.getViewModel(navHostController = nestedNavController),
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = Screen.TestsAttempted.route) {
                AttemptedTestsScreen(
                    navHostController = nestedNavController,
                    viewModel = viewModel,
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = Screen.PopularTests.route) {
                PopularTestsScreen(
                    navHostController = nestedNavController,
                    viewModel = viewModel,
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = Screen.SavedTests.route) {
                SavedTests(
                    navHostController = nestedNavController,
                    viewModel = viewModel,
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}