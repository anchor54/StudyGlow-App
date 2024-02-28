package com.example.studyglows.screens.testseries.subscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.testseries.viewmodel.TestSeriesViewModel
import com.example.studyglows.screens.testseries.components.ContinueTest
import com.example.studyglows.screens.testseries.components.RecommendedTests
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun TestDashboard(
    navHostController: NavHostController,
    viewModel: TestSeriesViewModel,
    sharedViewModel: SharedViewModel,
    modifier: Modifier = Modifier
) {
    val currentTests by viewModel.testsInProgress.collectAsState()
    val recommendedTests by viewModel.recommendedTests.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getCurrentlyAttemptingTests("")
        viewModel.getRecommendedTests()
    }

    Column(modifier = modifier.padding(16.dp)) {
        ContinueTest(
            modifier = Modifier.fillMaxWidth(),
            currentTests = currentTests,
            onExploreClicked = { navHostController.navigate(Screen.ExploreTest.route) },
            onTestClicked = { navHostController.navigate("${Screen.TestSeriesDetails.route}/${it}") },
            showAttemptedClicked = { navHostController.navigate(Screen.TestsAttempted.route)}
        )
        Spacer(modifier = Modifier.height(32.dp))
        RecommendedTests(
            modifier = Modifier.fillMaxWidth(),
            recommendedTestList = recommendedTests
        ) {
            navHostController.navigate("${Screen.TestSeriesDetails.route}/${it}")
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}