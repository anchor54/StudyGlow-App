package com.example.studyglows.screens.editorial_currentaffair.editorial.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialItem
import com.example.studyglows.utils.UIUtils.bottomBorder
import com.example.studyglows.utils.UIUtils.topBorder
import com.example.studyglows.utils.Utils
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun EditorialListItem(
    item: EditorialItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .topBorder(strokeWidth = 1.dp, color = Color(0xFFB1D4EA))
            .bottomBorder(strokeWidth = 1.dp, color = Color(0xFFB1D4EA))
            .padding(horizontal = 0.dp, vertical = 16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = Utils.getDate(item.date, "MMMM dd yyyy"),
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 15.6.sp,
                    color = Color(0xFF8798AD),
                    letterSpacing = 0.4.sp,
                )
            )
            Text(
                text = item.title,
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.1.sp,
                )
            )
        }
        GlideImage(
            imageModel = { item.image },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            ),
            modifier = Modifier.size(width = 90.dp, height = 50.dp)
        )
    }
}