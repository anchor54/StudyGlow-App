package com.example.studyglows.screens.testseries.subscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.testseries.TestSeriesViewModel
import com.example.studyglows.screens.testseries.components.ExamCategoryCard
import com.example.studyglows.screens.testseries.components.FreeMockTestCard
import com.example.studyglows.screens.testseries.components.PurchasableTestCard
import com.example.studyglows.shared.components.BaseScreenLayout
import com.example.studyglows.shared.components.ExploreSection
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun ExploreTestsScreen(
    navHostController: NavHostController,
    viewModel: TestSeriesViewModel,
    sharedViewModel: SharedViewModel,
    modifier: Modifier = Modifier
) {
    val popularTests by viewModel.popularTests.collectAsState()
    val freeTests by viewModel.freeMocks.collectAsState()
    val examCategory by viewModel.examCategories.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getPopularTests()
        viewModel.getFreeMockTests()
        viewModel.getExamCategories()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExploreSection(
            title = "Popular Test Series",
            items = popularTests,
            modifier = Modifier.padding(16.dp),
            onExpanded = { navHostController.navigate(Screen.PopularTests.route) }
        ) {
            PurchasableTestCard(
                modifier = Modifier.clickable { navHostController.navigate("${Screen.TestDetails.route}/testId=${it.id}") },
                testCardItem = it,
                onCardClicked = { navHostController.navigate("${Screen.TestDetails.route}?testId=${it.id}") }
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        ExploreSection(
            title = "Attempt your free mock tests",
            items = freeTests,
            modifier = Modifier.padding(16.dp)
        ) {
            FreeMockTestCard(
                modifier = Modifier.clickable { navHostController.navigate("${Screen.TestDetails.route}/testId=${it.id}") },
                testDetails = it
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        ExploreSection(
            title = "Find By Exam Categories",
            items = examCategory,
            modifier = Modifier.padding(16.dp)
        ) {
            ExamCategoryCard(
                modifier = Modifier.fillMaxWidth().clickable {},
                examCategory = it
            )
        }
    }
}