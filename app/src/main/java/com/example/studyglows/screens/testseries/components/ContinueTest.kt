package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.testseries.model.PurchasedTestItem

@Composable
fun ContinueTest(
    modifier: Modifier = Modifier,
    currentTests: List<PurchasedTestItem>,
    onExploreClicked: () -> Unit,
    onTestClicked: (id: String) -> Unit,
    showAttemptedClicked: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Continue Watching",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        if (currentTests.isEmpty()) {
            EmptyTestsInProgress(exploreTestsClicked = onExploreClicked)
            Spacer(modifier = Modifier.height(38.dp))
        } else {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                itemsIndexed(currentTests) { _, test ->
                    PurchasedTestCard(
                        testCardItem = test,
                        onCardClicked = { onTestClicked(test.id) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(44.dp))
        }
        Button(
            modifier = Modifier.padding(24.dp, 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(100.dp),
            elevation = ButtonDefaults.buttonElevation(10.dp),
            onClick = showAttemptedClicked
        ) {
            Text(
                text = "VIEW ALL YOUR ATTEMPTED TESTS",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF025284),
                    letterSpacing = 1.25.sp,
                )
            )
        }
    }
}