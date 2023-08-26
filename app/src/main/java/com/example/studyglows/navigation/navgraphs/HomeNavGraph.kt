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
import com.example.studyglows.screens.home.courseprofile.CourseDetailsScreen
import com.example.studyglows.screens.home.lecture.LectureScreen
import com.example.studyglows.shared.viewmodels.SharedViewModel

fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController,
    appVM: SharedViewModel
) {
    navigation(
        startDestination = "${Screen.Home.route}/{screenId}",
        route = Route.HOME_ROUTE.name
    ) {
        composable(
            route = Screen.Home.route + "/{screenId}",
            arguments = listOf(
                navArgument(name = "screenId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            HomeScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController = navHostController),
                sharedViewModel = appVM,
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
                viewModel = it.getViewModel(navHostController),
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
                viewModel = it.getViewModel(navHostController),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}