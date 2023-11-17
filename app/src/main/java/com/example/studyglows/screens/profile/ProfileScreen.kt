package com.example.studyglows.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.profile.components.ProfileContent
import com.example.studyglows.shared.components.HomeAppBar
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    val userDetails by viewModel.userData.collectAsState()
    val purchasedCourses by viewModel.purchasedCourses.collectAsState()
    val purchasedTests by viewModel.purchasedTestSeries.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getUserDetails()
        viewModel.getAllPurchasedCourses()
        viewModel.getAllPurchasedTests()
    }
    LaunchedEffect(key1 = loading) {
        sharedViewModel.isLoading(loading)
    }
    LaunchedEffect(key1 = error) {
        sharedViewModel.showError(error)
    }

    Column(modifier = modifier.background(Color(0xFFE6F1F8))) {
        HomeAppBar(
            onNavIconClicked = { sharedViewModel.sendUIEvent(AppUIEvent.ShowDrawer) },
            onSearchClicked = {}
        )
        ProfileContent(
            userDetails = userDetails,
            purchasedCourses = purchasedCourses,
            purchasedTestSeries = purchasedTests,
            modifier = Modifier.weight(1f)
        )
    }

}