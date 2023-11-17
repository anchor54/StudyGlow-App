package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TestLegendItem(
    color: Color,
    text: String
) {
    Row {
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color)
        )
        Spacer(modifier = Modifier.width(13.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.4.sp,
            )
        )
    }
}

@Composable
fun TestLegendItem(
    icon: Int,
    text: String
) {
    Row {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(13.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.4.sp,
            )
        )
    }
}