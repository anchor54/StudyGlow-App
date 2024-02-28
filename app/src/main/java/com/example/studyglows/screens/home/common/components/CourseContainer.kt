package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CourseContainer(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    title: AnnotatedString = AnnotatedString(""),
    subtitle: AnnotatedString = AnnotatedString(""),
    onClicked: () -> Unit = {},
    footer: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier.clickable { onClicked() }
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Card(
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                GlideImage(
                    imageModel = { imageUrl },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.FillWidth
                    ),
                    modifier = Modifier.height(100.dp)
                )
            }
            Spacer(modifier = Modifier
                .height(17.dp)
                .fillMaxWidth())
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 15.6.sp,
                    color = Color(0xFF8798AD),
                    letterSpacing = 0.4.sp,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = subtitle, modifier = Modifier.fillMaxWidth())
            footer()
        }
    }
}