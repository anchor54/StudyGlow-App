package com.example.studyglows.screens.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.utils.UIUtils.outerShadow

@Preview(showBackground = false)
@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    buttonText: String = "OK",
    backgroundColor: Color = Color.Gray,
    onClick: () -> Unit = {}
) {
    val buttonShadowColorTop = Color(0xE5FDFFFF)
    val buttonShadowColorBottom = Color(0xFFCFD9DF)
    Button(
        onClick = onClick,
        modifier = modifier
            .outerShadow(
                color = buttonShadowColorTop,
                offsetX = (-10).dp,
                offsetY = (-10).dp,
                blurRadius = 10.dp,
                borderRadius = 28.dp,
            )
            .outerShadow(
                color = buttonShadowColorBottom,
                offsetX = (10).dp,
                offsetY = (10).dp,
                blurRadius = 10.dp,
                spread = 7.dp,
                borderRadius = 28.dp
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(size = 28.dp))
            .fillMaxWidth(1f),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE6F1F8)),
    ) {
        Text(
            text = buttonText,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF025284),
                textAlign = TextAlign.Center,
                letterSpacing = 1.25.sp,
            )
        )
    }
}