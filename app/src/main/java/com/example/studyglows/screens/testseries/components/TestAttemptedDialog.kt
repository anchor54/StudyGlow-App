package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TestAttemptedDialog(
    onDismiss: () -> Unit,
    questionsAttempted: Map<String, Pair<Int, Int>>,
    modifier: Modifier = Modifier
) {
    val categorizedAttempts = questionsAttempted.map { it.value }
    val totalAttempted = categorizedAttempts.map { it.first }.reduce { acc, i -> acc + i }
    val totalQuestions = categorizedAttempts.map { it.second }.reduce { acc, i -> acc + i }
    Column(modifier = modifier.padding(16.dp)) {
        QuestionsAttemptedItem(category = "All", attempted = totalAttempted, total = totalQuestions)
        Divider(thickness = 1.dp, color = Color(0xFFB1D4EA), modifier = Modifier.padding(vertical = 14.dp))
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            questionsAttempted.map {
                QuestionsAttemptedItem(
                    category = it.key,
                    attempted = it.value.first,
                    total = it.value.second
                )
            }
        }
    }
}

@Composable
fun QuestionsAttemptedItem(
    category: String,
    attempted: Int,
    total: Int
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "$attempted/$total",
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 22.5.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.25.sp,
            )
        )
        LinearProgressIndicator(
            progress = attempted.toFloat() / total.toFloat(),
            color = Color(0xFF01304E),
            trackColor = Color(0xFFE6F1F8),
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(CircleShape)
        )
        Text(
            text = "$category Questions Attempted",
            style = TextStyle(
                fontSize = 10.sp,
                lineHeight = 12.sp,
                color = Color(0xFF8798AD),
                letterSpacing = 1.5.sp,
            )
        )
    }
}