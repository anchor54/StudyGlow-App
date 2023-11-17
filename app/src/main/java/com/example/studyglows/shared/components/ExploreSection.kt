package com.example.studyglows.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun<T> ExploreSection(
    modifier: Modifier = Modifier,
    title: String,
    items: List<out T> = listOf(),
    onExpanded: (() -> Unit)? = null,
    gridContent: @Composable (item: T) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 20.4.sp,
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.15.sp,
                )
            )
            onExpanded?.let { fn ->
                Text(
                    text = "VIEW ALL",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        letterSpacing = 1.25.sp,
                    ),
                    modifier = Modifier.clickable { fn() }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        VerticalGrid(
            columns = 2,
            items = items,
            rowSpacing = 16.dp,
            columnSpacing = 13.dp
        ) { item ->
            gridContent(item = item)
        }
    }
}