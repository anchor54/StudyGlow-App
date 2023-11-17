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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studyglows.screens.testseries.TestSeriesViewModel
import com.example.studyglows.screens.testseries.components.TestResultLegend
import com.example.studyglows.screens.testseries.components.TestResultOverview
import com.example.studyglows.screens.testseries.components.TestResultQuestionMap
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.example.studyglows.utils.UIUtils.bottomBorder
import com.example.studyglows.utils.UIUtils.topBorder

@Composable
fun TestResultScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    viewModel: TestSeriesViewModel
)  {
    val testId = navHostController.currentBackStackEntry?.arguments?.getString("testId") ?: ""
    val testResultDetails by viewModel.testResult.collectAsState()
    val searchResults by viewModel.searchResult.collectAsState()

    LaunchedEffect(key1 = testId) {
        viewModel.getTestResult(testId)
    }

    testResultDetails?.let {
        Column(modifier = Modifier
            .padding(26.dp)
            .fillMaxWidth()) {
            TestResultOverview(
                rank = it.userRank to it.totalRankers,
                score = it.score to it.totalScore,
                attempted = it.attempted to it.totalQuestions,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(25.dp))
            TestResultLegend(
                modifier = Modifier
                    .topBorder(1.dp, Color(0xFFB1D4EA))
                    .bottomBorder(1.dp, Color(0xFFB1D4EA))
                    .padding(vertical = 18.dp)
                    .fillMaxWidth(),
            )
            TestResultQuestionMap(
                testId = testId,
                questionList = it.categorizedStatus
            )
        }
    }
}