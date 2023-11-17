package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.studyglows.screens.testseries.model.TestAttemptDetails
import com.example.studyglows.shared.components.DynamicIcon
import com.example.studyglows.utils.UIUtils.endBorder
import com.example.studyglows.utils.Utils

@Composable
fun AttemptedTestItem(
    modifier: Modifier = Modifier,
    itemDetails: TestAttemptDetails,
    takeTestClicked: () -> Unit,
    viewResultClicked: () -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier
                .background(Color(0xFF8BBFDF))
                .padding(22.dp, 4.dp)) {
                Text(
                    text = Utils.getDate(itemDetails.date, "MMM dd, yyyy"),
                    style = TextStyle(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        color = Color(0xFFE6F1F8),
                        letterSpacing = 1.5.sp,
                    )
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DynamicIcon(
                icon = itemDetails.icon,
                modifier = Modifier
                    .height(43.dp)
                    .width(43.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = itemDetails.title,
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 20.4.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.15.sp,
                    )
                )
                Text(
                    text = "${itemDetails.obtainedMarks}/${itemDetails.totalMarks} Marks",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF8798AD),
                        letterSpacing = 0.4.sp,
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(thickness = 1.dp, color = Color(0xFFB1D4EA))
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { takeTestClicked() },
                modifier = Modifier
                    .weight(1f)
                    .endBorder(
                        strokeWidth = 1.dp,
                        color = Color(0xFFB1D4EA)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "RETAKE TEST",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        letterSpacing = 1.25.sp,
                    )
                )
            }
            Button(
                onClick = { viewResultClicked() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                enabled = itemDetails.hasResult
            ) {
                Text(
                    text = if (itemDetails.hasResult) "VIEW RESULT" else "UNDER EVALUATION",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = if (!itemDetails.hasResult) Color(0xFF8BBFDF) else Color(0xFF025284),
                        textAlign = TextAlign.Center,
                        letterSpacing = 1.25.sp,
                    )
                )
            }
        }
    }
}