package com.example.studyglows.navigation

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.studyglows.navigation.navgraphs.authNavGraph
import com.example.studyglows.navigation.navgraphs.cartNavGraph
import com.example.studyglows.navigation.navgraphs.currentAffairsNavGraph
import com.example.studyglows.navigation.navgraphs.editorialNavGraph
import com.example.studyglows.navigation.navgraphs.getViewModel
import com.example.studyglows.navigation.navgraphs.homeNavGraph
import com.example.studyglows.navigation.navgraphs.settingsNavGraph
import com.example.studyglows.screens.editorial_currentaffair.saved.SavedScreen
import com.example.studyglows.screens.editorial_currentaffair.vacancies.LatestVacancies
import com.example.studyglows.screens.profile.ProfileScreen
import com.example.studyglows.screens.test.TestFlowContainer
//import com.example.studyglows.screens.testseries.BaseTestScreen
import com.example.studyglows.screens.testseries.TestSeriesFlowContainer
import com.example.studyglows.screens.welcome.WelcomeScreen
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    appVM: SharedViewModel
) {
    NavHost(
        navController = navHostController,
//        startDestination = Screen.TestSeriesScreen.route + "?params={screen_params}",
        startDestination = Screen.Welcome.route,
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
        composable(
            route = Screen.TestSeriesScreen.route + "?params={screen_params}",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = DeepLink.TestSeriesScreen
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument(name = "screen_params") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) {
            TestSeriesFlowContainer(
                modifier = Modifier.fillMaxSize(),
                sharedViewModel = appVM,
                viewModel = it.getViewModel(navHostController = navHostController),
                navHostController = navHostController
            )
        }
        composable(
            route = Screen.TestScreen.route + "?params={screen_params}",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = DeepLink.TestSeriesScreen
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument(name = "screen_params") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) {
            TestFlowContainer(
                modifier = Modifier.fillMaxSize(),
                appVm = appVM,
                viewModel = it.getViewModel(navHostController = navHostController),
                navHostController = navHostController
            )
        }
        authNavGraph(navHostController = navHostController, appVM = appVM)
        homeNavGraph(navHostController = navHostController, appVM = appVM)
        cartNavGraph(navHostController = navHostController, appVM = appVM)
        settingsNavGraph(navHostController = navHostController, appVM = appVM)
        editorialNavGraph(navHostController = navHostController, appVM = appVM)
        currentAffairsNavGraph(navHostController = navHostController, appVm = appVM)
//        testNavGraph(navHostController = navHostController, appVm = appVM)
    }
}