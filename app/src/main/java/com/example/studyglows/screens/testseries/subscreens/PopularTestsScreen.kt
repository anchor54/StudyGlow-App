package com.example.studyglows.screens.testseries.subscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.testseries.TestSeriesViewModel
import com.example.studyglows.screens.testseries.components.PurchasableTestCard
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun PopularTestsScreen(
    modifier: Modifier = Modifier,
    viewModel: TestSeriesViewModel,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    val popularTests by viewModel.popularTests.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getPopularTests()
    }

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Text(
            text = "Popular Test Series for SSC",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(22.dp),
            horizontalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            items(popularTests.size) {
                val test = popularTests[it]
                PurchasableTestCard(testCardItem = test) {
                    navHostController.navigate("${Screen.TestSeriesDetails.route}/${test.id}")
                }
            }
        }
    }
}