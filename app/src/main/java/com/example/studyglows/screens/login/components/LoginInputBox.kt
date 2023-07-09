package com.example.studyglows.screens.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.utils.UIUtils.innerShadow

@Preview(showBackground = false)
@Composable
fun LoginInputBox(
    text: String = "",
    staticText: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (String) -> Unit = {}
) {
    val textFieldValue by remember { mutableStateOf(text) }
    val buttonShadowColorBottom = Color(0xFFFDFFFF)
    val buttonShadowColorTop = Color(0xE6CFD9DF)
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(
                color = Color(0xFFE6F1F8),
                shape = RoundedCornerShape(size = 28.dp)
            )
            .innerShadow(
                blur = 10.dp,
                color = buttonShadowColorTop,
                cornersRadius = 68.dp,
                offsetY = 8.dp,
                offsetX = 8.dp
            )
            .innerShadow(
                blur = 10.dp,
                color = buttonShadowColorBottom,
                cornersRadius = 68.dp,
                offsetY = (-8).dp,
                offsetX = (-8).dp
            )
            .padding(16.dp)
    ) {
        Text(
            text = staticText,
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF025284),
                letterSpacing = 0.4.sp,
            )
        )
        BasicTextField(
            value = textFieldValue,
            onValueChange = onTextChanged,
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(color = Color(0xFFE6F1F8)),
            textStyle = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF025284),
                letterSpacing = 0.4.sp,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}