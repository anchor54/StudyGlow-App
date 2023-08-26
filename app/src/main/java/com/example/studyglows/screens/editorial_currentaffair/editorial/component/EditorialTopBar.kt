package com.example.studyglows.screens.editorial_currentaffair.editorial.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R

@Composable
fun EditorialTopBar(
    modifier: Modifier = Modifier,
    onLogoClicked: () -> Unit,
    onSavedClicked: () -> Unit,
    onShareClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.study_glow_small_icon),
            contentDescription = "Study Glow Icon",
            modifier = Modifier.clickable { onLogoClicked() }
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "STUDY GLOWS",
            style = TextStyle(
                fontSize = 22.sp,
                color = Color(0xFF01304E),
                fontWeight = FontWeight(900),
                letterSpacing = 2.2.sp,
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.bookmark),
            contentDescription = "Save Item",
            modifier = Modifier.clickable { onSavedClicked() }
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.share),
            contentDescription = "Share item",
            modifier = Modifier.clickable { onShareClicked() }
        )
    }
}