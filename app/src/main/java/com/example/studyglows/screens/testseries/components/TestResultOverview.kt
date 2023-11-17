package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R

@Composable
fun TestResultOverview(
    modifier: Modifier = Modifier,
    rank: Pair<Int, Int>,
    score: Pair<Int, Int>,
    attempted: Pair<Int, Int>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        TestResultOverviewItem(
            modifier = Modifier.fillMaxWidth(),
            icon = R.drawable.flag,
            title = "Rank",
            values = rank
        )
        TestResultOverviewItem(
            modifier = Modifier.fillMaxWidth(),
            icon = R.drawable.trophy,
            title = "Score",
            values = score
        )
        TestResultOverviewItem(
            modifier = Modifier.fillMaxWidth(),
            icon = R.drawable.file_solid,
            title = "Attempted",
            values = attempted
        )
    }
}

@Composable
fun TestResultOverviewItem(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    values: Pair<Int, Int>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = "Rank",
                tint = Color(0xFF025284)
            )
            Spacer(modifier = Modifier.width(13.dp))
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 20.4.sp,
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.15.sp,
                )
            )
        }
        val score = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color(0xFF2E384D))) {
                append(values.first.toString())
            }
            withStyle(style = SpanStyle(color = Color(0xFF8798AD))) {
                append(" / ${values.second}")
            }
        }
        Text(
            text = score,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF2E384D),
            )
        )
    }
}