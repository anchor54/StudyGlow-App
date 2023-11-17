package com.example.studyglows.shared.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun TagChip(
    text: String = "",
    modifier: Modifier = Modifier
) {
    if (text.isEmpty()) return
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF01304E)
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp, 2.dp),
            style = TextStyle(
                color = Color.White
            ),
        )
    }
}