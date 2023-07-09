package com.example.studyglows.screens.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginField(
    modifier: Modifier = Modifier,
    text: String,
    pretext: String = "",
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 10.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF8798AD),
                letterSpacing = 1.5.sp
            )
        )
        LoginInputBox(
            text = text,
            staticText = pretext,
            onTextChanged = onTextChanged,
            keyboardType = keyboardType,
        )
    }
}