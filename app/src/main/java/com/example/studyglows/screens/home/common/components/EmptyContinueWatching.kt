package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun EmptyContinueWatching(
    onProceedClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Nothing here?",
            style = TextStyle(
                fontSize = 21.sp,
                lineHeight = 25.2.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Add Your favourite courses",
            style = TextStyle(
                fontSize = 25.sp,
                lineHeight = 30.sp,
                color = Color(0xFF2E384D),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.width(215.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.padding(24.dp, 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF025284)
            ),
            onClick = onProceedClicked
        ) {
            Text(
                text = "Explore courses",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    letterSpacing = 1.25.sp,
                )
            )
        }
    }
}