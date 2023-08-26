package com.example.studyglows.screens.editorial_currentaffair.current_affairs.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BulletPoint(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .size(6.dp)
            .clip(RoundedCornerShape(6.dp)))
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 22.5.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.25.sp,
            )
        )
    }
}