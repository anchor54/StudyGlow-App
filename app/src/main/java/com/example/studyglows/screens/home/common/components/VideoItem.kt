package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.home.common.models.VideoModel
import com.example.studyglows.utils.UIUtils.bottomBorder
import com.example.studyglows.utils.UIUtils.topBorder
import com.example.studyglows.utils.Utils

@Composable
fun VideoItem(
    video: VideoModel,
    playlistIndex: Int,
    videoIndex: Int,
    modifier: Modifier = Modifier,
    onVideoClicked: (Int, Int) -> Unit
) {
    Box(
        modifier = modifier
            .topBorder(1.dp, Color(0xFFB1D4EA))
            .bottomBorder(1.dp, Color(0xFFB1D4EA))
            .clickable { onVideoClicked(playlistIndex, videoIndex) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp, 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = video.title,
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 20.4.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.15.sp,
                    )
                )
                Text(
                    text = Utils.getFormattedDurationString(video.videoLength),
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF8798AD),
                        letterSpacing = 0.4.sp,
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            VideoPlayChip()
        }
    }
}

@Composable
fun VideoCompletedChip(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFB1D4EA)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = "COMPLETED",
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            style = TextStyle(
                fontSize = 10.sp,
                lineHeight = 12.sp,
                color = Color(0xFF023F66),
                letterSpacing = 1.5.sp,
            )
        )
    }
}

@Composable
fun VideoPlayingChip(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFF023F66),
        )
    ) {
        Text(
            text = "PLAYING",
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            style = TextStyle(
                fontSize = 10.sp,
                lineHeight = 12.sp,
                color = Color(0xFF8798AD),
                letterSpacing = 1.5.sp,
            )
        )
    }
}

@Composable
fun VideoPlayChip(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF025284)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = "PLAY",
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            style = TextStyle(
                fontSize = 10.sp,
                lineHeight = 12.sp,
                color = Color(0xFFE6F1F8),
                letterSpacing = 1.5.sp,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVideoItem() {
    VideoItem(
        playlistIndex = 0,
        videoIndex = 0,
        video = VideoModel(
            title = "History Lesson 1",
            videoLength = 324,
            videoLink = "https://www.youtube.com/watch?v=9c1NIrAqXe4",
            isViewable = true,
        )
    ) { _, _ -> }
}