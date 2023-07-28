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
import com.example.studyglows.screens.home.HomeScreen
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.allcourses.AllCoursesScreen
import com.example.studyglows.screens.home.courseprofile.CourseDetailsScreen
import com.example.studyglows.screens.home.lecture.LectureScreen

fun NavGraphBuilder.dashboardNavGraph(
    navHostController: NavHostController,
    viewModel: HomeViewModel? = null
) {
    navigation(
        startDestination = Screen.Home.route,
        route = Route.DASHBOARD_ROUTE.name
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navHostController = navHostController,
                viewModel = viewModel ?: it.getViewModel(navHostController = navHostController),
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            route = Screen.Lecture.route + "?courseId={courseId}",
            arguments = listOf(
                navArgument(name = "courseId") {
                    type = NavType.StringType
                }
            )
        ) {
            LectureScreen(
                navHostController = navHostController,
                viewModel = viewModel ?: it.getViewModel(navHostController),
                modifier = Modifier.fillMaxSize()
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
            route = Screen.CourseDetails.route + "?courseId={courseId}",
            arguments = listOf(
                navArgument(name = "courseId") {
                    type = NavType.StringType
                }
            )
        ) {
            CourseDetailsScreen(
                navHostController = navHostController,
                viewModel = viewModel ?: it.getViewModel(navHostController),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}