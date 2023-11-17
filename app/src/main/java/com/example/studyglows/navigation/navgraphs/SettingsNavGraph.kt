package com.example.studyglows.navigation.navgraphs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.setting.SettingsScreen
import com.example.studyglows.shared.viewmodels.SharedViewModel

fun NavGraphBuilder.settingsNavGraph(
    navHostController: NavHostController,
    appVM: SharedViewModel
) {
    navigation(
        route = Route.SETTINGS_ROUTE.name,
        startDestination = Screen.Settings.route
    ) {
        composable(route = Screen.Settings.route) {
            SettingsScreen(
                navHostController = navHostController,
                viewModel = it.getViewModel(navHostController = navHostController),
                sharedViewModel = appVM,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}