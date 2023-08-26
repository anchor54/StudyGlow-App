package com.example.studyglows.screens.editorial_currentaffair.editorial.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialItem
import com.example.studyglows.utils.Utils
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TopEditorialItem(
    modifier: Modifier = Modifier,
    item: EditorialItem
) {
    Column(modifier = modifier) {
        GlideImage(
            imageModel = { item.image },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(182.dp)
                .clip(RoundedCornerShape(21.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = Utils.getDate(item.date, "MMMM dd yyyy"),
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                color = Color(0xFF000000),
                letterSpacing = 0.4.sp,
            )
        )
        Text(
            text = item.title,
            style = TextStyle(
                fontSize = 21.sp,
                lineHeight = 25.2.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
                letterSpacing = 0.15.sp,
            )
        )
    }
}