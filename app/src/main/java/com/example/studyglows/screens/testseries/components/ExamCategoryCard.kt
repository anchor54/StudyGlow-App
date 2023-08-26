package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.studyglows.screens.testseries.model.ExamCategory
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ExamCategoryCard(
    modifier: Modifier = Modifier,
    examCategory: ExamCategory
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = examCategory.examName,
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.1.sp,
                )
            )
            Spacer(modifier = Modifier.width(14.dp))
            GlideImage(
                imageModel = { examCategory.icon },
                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit
                ),
                modifier = Modifier
                    .heightIn(min = 26.dp, max = 40.dp)
                    .width(32.dp)
            )
        }
    }
}