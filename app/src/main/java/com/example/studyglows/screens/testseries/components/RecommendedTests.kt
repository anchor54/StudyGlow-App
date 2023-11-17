package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.testseries.model.TestCardItem

@Composable
fun RecommendedTests(
    modifier: Modifier = Modifier,
    recommendedTestList: List<TestCardItem>,
    onTestClicked: (id: String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Recommended Test Series",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.height(13.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(recommendedTestList.size) {
                PurchasableTestCard(testCardItem = recommendedTestList[it]) {
                    onTestClicked(recommendedTestList[it].id)
                }
            }
        }
    }
}