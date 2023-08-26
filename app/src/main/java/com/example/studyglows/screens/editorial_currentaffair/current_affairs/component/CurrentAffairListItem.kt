package com.example.studyglows.screens.editorial_currentaffair.current_affairs.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
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
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairItem
import com.example.studyglows.utils.UIUtils.bottomBorder
import com.example.studyglows.utils.UIUtils.topBorder
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CurrentAffairListItem(
    modifier: Modifier = Modifier,
    currentAffair: CurrentAffairItem
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .topBorder(strokeWidth = 1.dp, color = Color(0xFFB1D4EA))
                .bottomBorder(strokeWidth = 1.dp, color = Color(0xFFB1D4EA))
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentAffair.title,
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.1.sp,
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            GlideImage(
                imageModel = { currentAffair.image },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                ),
                modifier = Modifier.size(width = 90.dp, height = 50.dp)
            )
        }
    }
}