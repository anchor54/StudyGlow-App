package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studyglows.R

@Composable
fun TestResultLegend(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(23.dp)) {
            TestLegendItem(icon = R.drawable.error, text = "Wrong")
            TestLegendItem(color = Color(0xFF00D408), text = "Attempted")
        }
        Column(verticalArrangement = Arrangement.spacedBy(23.dp)) {
            TestLegendItem(color = Color(0xFFFF2C45), text = "Unattempted")
            TestLegendItem(color = Color(0xFF025284), text = "Unseen")
        }
    }
}