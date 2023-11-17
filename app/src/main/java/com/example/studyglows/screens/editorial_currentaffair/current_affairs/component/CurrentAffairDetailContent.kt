package com.example.studyglows.screens.editorial_currentaffair.current_affairs.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairDetails
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CurrentAffairDetailContent(
    modifier: Modifier = Modifier,
    details: CurrentAffairDetails,
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.fillMaxWidth().fillMaxHeight(0.8f)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            GlideImage(
                imageModel = { details.image },
                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = details.title,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 20.4.sp,
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.15.sp,
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                details.contents.map { BulletPoint(text = it) }
            }
        }
    }
}
