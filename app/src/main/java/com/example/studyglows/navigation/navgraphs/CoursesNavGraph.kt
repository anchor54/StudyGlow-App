package com.example.studyglows.navigation.navgraphs

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.dashboard.DashboardScreen
import com.example.studyglows.screens.home.explore.ExploreCoursesScreen

fun NavGraphBuilder.coursesNavGraph(
    navHostController: NavHostController,
    viewModel: HomeViewModel? = null,
    modifier: Modifier = Modifier
) {
    navigation(
        startDestination = Screen.Dashboard.route,
        route = Route.COURSE_ROUTE.name
    ) {
        composable(route = Screen.Dashboard.route) {
            DashboardScreen(
                modifier = modifier,
                navHostController = navHostController,
                viewModel = viewModel ?: it.getViewModel(navHostController = navHostController)
            )
        }
        composable(route = Screen.Explore.route) {
            ExploreCoursesScreen(
                modifier = modifier,
                navHostController = navHostController,
                viewModel = viewModel ?: it.getViewModel(navHostController)
            )
        }
    }
}