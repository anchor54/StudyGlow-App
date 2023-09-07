package com.example.studyglows.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.studyglows.navigation.navgraphs.authNavGraph
import com.example.studyglows.navigation.navgraphs.cartNavGraph
import com.example.studyglows.navigation.navgraphs.currentAffairsNavGraph
import com.example.studyglows.navigation.navgraphs.editorialNavGraph
import com.example.studyglows.navigation.navgraphs.getViewModel
import com.example.studyglows.navigation.navgraphs.homeNavGraph
import com.example.studyglows.navigation.navgraphs.settingsNavGraph
import com.example.studyglows.navigation.navgraphs.testSeriesNavGraph
import com.example.studyglows.screens.editorial_currentaffair.saved.SavedScreen
import com.example.studyglows.screens.editorial_currentaffair.vacancies.LatestVacancies
import com.example.studyglows.screens.profile.ProfileScreen
import com.example.studyglows.screens.testseries.TestSeriesScreen
import com.example.studyglows.screens.testseries.subscreens.testscreens.TestScreen
import com.example.studyglows.screens.welcome.WelcomeScreen
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    appVM: SharedViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.TestScreen.route + "?testId=234",
        route = Route.ROOT_ROUTE.name
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(
                navHostController = navHostController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                viewModel = it.getViewModel(navHostController = navHostController),
                navHostController = navHostController,
                sharedViewModel = appVM,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Screen.Vacancies.route) {
            LatestVacancies(
                viewModel = it.getViewModel(navHostController = navHostController),
                navHostController = navHostController,
                modifier = Modifier.fillMaxSize(),
                sharedViewModel = appVM
            )
        }
        composable(route = Screen.SavedVacancies.route) {
            SavedScreen(
                viewModel = it.getViewModel(navHostController = navHostController),
                navHostController = navHostController,
                modifier = Modifier.fillMaxSize(),
                sharedViewModel = appVM
            )
        }
        composable(route = Screen.TestSeriesScreen.route) {
            TestSeriesScreen(
                viewModel = it.getViewModel(navHostController = navHostController),
                navHostController = navHostController,
                modifier = Modifier.fillMaxSize(),
                sharedViewModel = appVM
            )
        }
        composable(
            route = Screen.TestScreen.route + "?testId={testId}",
            arguments = listOf(
                navArgument(name = "testId") {
                    type = NavType.StringType
                }
            )
        ) {
            TestScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController),
                sharedViewModel = appVM,
                modifier = Modifier.fillMaxSize()
            )
        }
        authNavGraph(navHostController = navHostController)
        homeNavGraph(navHostController = navHostController, appVM = appVM)
        cartNavGraph(navHostController = navHostController)
        settingsNavGraph(navHostController = navHostController)
        editorialNavGraph(navHostController = navHostController, appVM = appVM)
        currentAffairsNavGraph(navHostController = navHostController, appVm = appVM)
    }
}