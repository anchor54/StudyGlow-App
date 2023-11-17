package com.example.studyglows.screens.testseries.subscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.TestSeriesUIEvent
import com.example.studyglows.screens.testseries.TestSeriesViewModel
import com.example.studyglows.screens.testseries.components.AttemptedTestItem
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.example.studyglows.utils.Utils

@Composable
fun AttemptedTestsScreen(
    modifier: Modifier = Modifier,
    viewModel: TestSeriesViewModel,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    val _attemptedTests by viewModel.attemptedTests.collectAsState()
    val attemptedTests by remember(_attemptedTests) {
        derivedStateOf {
            _attemptedTests.sortedBy { -it.date }.groupBy { Utils.getDate(it.date, "MMM, yyyy") }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getAttemptedTests("")
    }

    Column(modifier = modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
    ) {
        attemptedTests.forEach {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = it.key,
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF2E384D),
                            letterSpacing = 0.1.sp,
                        ),
                        modifier = Modifier.padding(horizontal = 13.dp, vertical = 20.dp)
                    )
                }
                it.value.map { test ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE6F1F8))
                            .padding(start = 18.dp)
                    ) {
                        AttemptedTestItem(
                            itemDetails = test,
                            takeTestClicked = { viewModel.sendUIEvent(TestSeriesUIEvent.OpenTestScreen(test.testId)) },
                            viewResultClicked = { navHostController.navigate(Screen.TestResults.route + "/${test.testId}") },
                        )
                    }
                }
            }
        }
    }
}