package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContinueWatchingCard(
    imageUrl: String,
    courseName: String,
    lastChapterName: String,
    progress: Float = 0f
) {
    CourseContainer(
        imageUrl = imageUrl,
        title = AnnotatedString(courseName),
        subtitle = AnnotatedString(lastChapterName),
        modifier = Modifier.padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        LinearProgressIndicator(
            progress = progress,
            color = Color(0xFF01304E),
            trackColor = Color(0xFFE6F1F8),
            modifier = Modifier
                .width(154.dp)
                .height(10.dp)
                .clip(CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContinueWatchingCard() {
    ContinueWatchingCard(
        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
        courseName = "Public Speaking",
        lastChapterName = "Chapter 3",
        progress = 0.2f
    )
}