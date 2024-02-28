package com.example.studyglows.screens.testseries.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.testseries.model.QuestionState
import com.example.studyglows.shared.model.CategorizedMap

@Composable
fun TestDetails(
    modifier: Modifier = Modifier,
    onSubmit: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        TestLegend(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp))
        Divider(thickness = 1.dp, color = Color(0xFFB1D4EA))
        content()
        Button(
            onClick = onSubmit,
            colors = ButtonDefaults.buttonColors(Color(0xFF025284)),
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "SUBMIT TEST",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.25.sp,
                )
            )
        }
    }
}