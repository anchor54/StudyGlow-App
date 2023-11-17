package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = false)
@Composable
fun FAQItem(
    question: String = "",
    answer: String = "",
    modifier: Modifier = Modifier
) {
    if (question.isEmpty() || answer.isEmpty()) return
    var showAnswer by remember { mutableStateOf(false) }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showAnswer = !showAnswer },
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)) {
                Text(
                    text = question,
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.4.sp,
                    )
                )
            }
        }
        if (showAnswer) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 8.dp)
                ) {
                    Text(
                        text = answer,
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            color = Color(0xFF8798AD),
                            letterSpacing = 0.4.sp,
                        )
                    )
                }
            }
        }
    }
}