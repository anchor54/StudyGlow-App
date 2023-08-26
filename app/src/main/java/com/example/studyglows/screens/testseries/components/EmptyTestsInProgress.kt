package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyTestsInProgress(
    modifier: Modifier = Modifier,
    exploreTestsClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Find your perfect test-series",
            style = TextStyle(
                fontSize = 25.sp,
                lineHeight = 30.sp,
                color = Color(0xFF2E384D),
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.padding(24.dp, 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF025284)
            ),
            onClick = exploreTestsClicked
        ) {
            Text(
                text = "Explore Test series",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.25.sp,
                )
            )
        }
    }
}