package com.example.studyglows.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterChip(
    modifier: Modifier = Modifier,
    filterName: String = "",
    onFilterRemoved: () -> Unit
) {
    Box(modifier = modifier
        .background(color = Color(0xFF025284), shape = RoundedCornerShape(20.dp))) {
        Row(
            modifier = Modifier
                .clickable { onFilterRemoved }
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = filterName,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color(0xFFE6F1F8),
                ),
            )
            Icon(imageVector = Icons.Rounded.Clear, tint = Color.White, contentDescription = "remove filter")
        }
    }
}