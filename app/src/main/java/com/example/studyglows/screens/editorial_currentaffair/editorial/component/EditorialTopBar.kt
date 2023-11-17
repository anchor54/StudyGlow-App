package com.example.studyglows.screens.editorial_currentaffair.editorial.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.example.studyglows.shared.components.SaveIcon
import com.example.studyglows.shared.components.ShareButton

@Composable
fun EditorialTopBar(
    modifier: Modifier = Modifier,
    onLogoClicked: () -> Unit,
    onSavedClicked: () -> Unit,
    shareLink: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
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
        SaveIcon(onSavedClicked = onSavedClicked)
        ShareButton(deeplink = shareLink)
    }
}