package com.example.studyglows.screens.testseries.subscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.TestSeriesUIEvent
import com.example.studyglows.screens.testseries.TestSeriesViewModel
import com.example.studyglows.screens.testseries.components.TestListItem
import com.example.studyglows.shared.components.DynamicIcon
import com.example.studyglows.shared.components.TabLayout
import com.example.studyglows.shared.components.VerticalGrid
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun TestDetailsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    viewModel: TestSeriesViewModel
) {
    val testDetails by viewModel.testSeriesDetails.collectAsState()
    val testId = navHostController.currentBackStackEntry?.arguments?.getString("testId") ?: ""
    LaunchedEffect(key1 = testId) {
        viewModel.getTestSeriesDetails(testId)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DynamicIcon(
                icon = testDetails.icon,
                modifier = Modifier
                    .width(31.dp)
                    .height(31.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.bookmark),
                    contentDescription = "bookmark test",
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.share),
                    contentDescription = "share test link",
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
        }
        Text(
            text = "SBI CLERK Test Series ",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(11.dp))
        Divider(
            thickness = 1.dp,
            color = Color(0xFFB1D4EA),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(7.dp))
        VerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            columns = 2,
            items = listOf(
                "${testDetails.fullTests.size} Full Tests- Prelims",
                "${testDetails.prevPapers.size} Previous Paper",
                "${testDetails.chapterTests.size} Chapter Test",
                "${testDetails.sectionTests.size} Fixed Section Test"
            )
        ) {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 15.6.sp,
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.4.sp,
                )
            )
        }
        Spacer(modifier = Modifier.height(13.dp))
        TabLayout(
            modifier = Modifier.fillMaxWidth(),
            tabNames = listOf(
                "Full Tests",
                "Previous Papers",
                "Chapter Tests"
            )
        ) { tabIdx ->
            Box(modifier = Modifier.fillMaxWidth().background(Color(0xFFE6F1F8))) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    val listContent = when (tabIdx) {
                        0 -> testDetails.fullTests
                        1 -> testDetails.prevPapers
                        2 -> testDetails.chapterTests
                        else -> listOf()
                    }
                    items(listContent.size) {
                        TestListItem(
                            itemDetails = listContent[it],
                            modifier = Modifier.clickable {
                                viewModel.sendUIEvent(
                                    TestSeriesUIEvent.OpenTestScreen(listContent[it].id)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}