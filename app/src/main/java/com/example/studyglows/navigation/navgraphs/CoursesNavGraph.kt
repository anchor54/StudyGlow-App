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
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.allcourses.AllCoursesScreen
import com.example.studyglows.screens.home.dashboard.CoursesDashboard
import com.example.studyglows.screens.home.explore.ExploreCoursesScreen
import com.example.studyglows.screens.home.filter.FilterCoursesScreen
import com.example.studyglows.screens.saved.SavedCoursesScreen

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
            CoursesDashboard(
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
        composable(
            route = Screen.AllCourses.route + "?subjectId={subjectId}",
            arguments = listOf(
                navArgument(name = "subjectId") {
                    type = NavType.StringType
                }
            )
        ) {
            AllCoursesScreen(
                navHostController = navHostController,
                viewModel = viewModel ?: it.getViewModel(navHostController),
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            route = Screen.FilterCourses.route + "?title={title}",
            arguments = listOf(
                navArgument(name = "title") {
                    type = NavType.StringType
                }
            )
        ) {
            FilterCoursesScreen(
                modifier = modifier,
                navHostController = navHostController,
                viewModel = viewModel ?: it.getViewModel(navHostController)
            )
        }
        composable(route = Screen.SavedCourses.route) {
            SavedCoursesScreen(
                modifier = modifier,
                navHostController = navHostController,
                viewModel = viewModel ?: it.getViewModel(navHostController)
            )
        }
    }
}