package com.example.studyglows.screens.testseries

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.navigation.navgraphs.testsNavGraph
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.testseries.model.testNavDrawerContent
import com.example.studyglows.shared.components.BaseScreenLayout
import com.example.studyglows.shared.components.drawermenu.BaseDrawerNavigation
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun TestSeriesScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: TestSeriesViewModel,
    sharedViewModel: SharedViewModel
) {
    val testNavHost = rememberNavController()

    LaunchedEffect(key1 = Unit) {
        sharedViewModel.setDrawerMidOptions(
            options = testNavDrawerContent(),
            handler = object : BaseDrawerNavigation(navHostController) {
                override fun handleDrawerNavigation(itemId: String) {
                    when (itemId) {
                        "my_tests" -> { testNavHost.navigate(Route.TEST_SERIES.name) }
                        "explore_tests" -> { testNavHost.navigate(Screen.ExploreTest.route) }
                        "evaluation" -> { testNavHost.navigate(Screen.TestsAttempted.route) }
                        "saved_tests" -> { testNavHost.navigate(Screen.SavedTests.route) }
                        else -> super.handleDrawerNavigation(itemId)
                    }
                }
            }
        )
    }

    BaseScreenLayout(
        modifier = modifier,
        openDrawer = { sharedViewModel.sendUIEvent(AppUIEvent.ShowDrawer()) }
    ) {
        NavHost(
            navController = testNavHost,
            startDestination = Route.TEST_SERIES.name,
            route = Route.TEST_ROOT.name
        ) {
            testsNavGraph(
                navHostController = testNavHost,
                appVM = sharedViewModel
            )
        }
    }
}