package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TestCardButton(
    modifier: Modifier = Modifier,
    content: String = ""
) {
    Card(
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        ),
        colors = CardDefaults.cardColors(Color(0xFF025284)),
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
            text = content,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFE6F1F8),
                letterSpacing = 0.1.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}