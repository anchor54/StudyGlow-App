package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.testseries.model.FreeMockTestModule
import com.example.studyglows.utils.Utils

@Composable
fun FreeMockTestCard(
    modifier: Modifier = Modifier,
    testDetails: FreeMockTestModule
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier.width(width = 160.dp),
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 7.dp)
        ) {
            Text(
                text = testDetails.title,
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.1.sp,
                )
            )
            Spacer(modifier = Modifier.height(height = 7.dp))
            Text(
                text = "Expires on ${Utils.getDate(testDetails.expiresAt, "dd/mm/yy")}",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 15.6.sp,
                    color = Color(0xFF8798AD),
                    letterSpacing = 0.4.sp,
                )
            )
            Spacer(modifier = Modifier.height(height = 10.dp))
            Divider(thickness = 1.dp, color = Color(0xFF025284))
            Spacer(modifier = Modifier.height(height = 10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Questions",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF025284),
                        letterSpacing = 0.4.sp,
                    )
                )
                Text(
                    text = testDetails.totalQuestions.toString(),
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF025284),
                        letterSpacing = 0.4.sp,
                    )
                )
            }
            Spacer(modifier = Modifier.height(height = 5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Max Marks",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF025284),
                        letterSpacing = 0.4.sp,
                    )
                )
                Text(
                    text = testDetails.maxMarks.toString(),
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF025284),
                        letterSpacing = 0.4.sp,
                    )
                )
            }
            Spacer(modifier = Modifier.height(height = 5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Time",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF025284),
                        letterSpacing = 0.4.sp,
                    )
                )
                Text(
                    text = "${testDetails.duration} mins",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF025284),
                        letterSpacing = 0.4.sp,
                    )
                )
            }
            Spacer(modifier = Modifier.height(height = 14.dp))
            TestCardButton(modifier = Modifier.fillMaxWidth(), content = "Attempt")
        }
    }
}

@Preview
@Composable
fun PreviewFreeMockTestCard() {
    FreeMockTestCard(
        testDetails = FreeMockTestModule(
            title = "RRB OA Prelims Mock Test 1",
            expiresAt = 1690050600000L,
            maxMarks = 80,
            totalQuestions = 80,
            duration = 45
        )
    )
}