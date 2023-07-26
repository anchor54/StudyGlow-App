package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.example.studyglows.screens.home.common.models.PlaylistModel
import com.example.studyglows.screens.home.common.models.VideoModel
import com.example.studyglows.utils.UIUtils.bottomBorder
import com.example.studyglows.utils.UIUtils.topBorder

@Composable
fun PlaylistItem(
    modifier: Modifier = Modifier,
    playlistIndex: Int,
    playlist: PlaylistModel,
    onVideoClicked: (Int, Int) -> Unit
) {
    var showPlaylist by remember { mutableStateOf(false) }
    val intractable by remember {
        derivedStateOf {
            playlist.videos.find { it.isViewable } != null
        }
    }

    Box(modifier = modifier.padding(16.dp, 0.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showPlaylist = intractable && !showPlaylist }
                    .topBorder(1.dp, Color(0xFFB1D4EA))
                    .bottomBorder(1.dp, Color(0xFFB1D4EA))
                    .padding(16.dp, 16.dp)
            ) {
                Box(modifier = Modifier
                    .size(43.dp)
                    .background(
                        color = Color(0xFFD9D9D9),
                        shape = RoundedCornerShape(8.dp)
                    )
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column {
                    Text(
                        text = playlist.title,
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 20.4.sp,
                            color = Color(0xFF2E384D),
                            letterSpacing = 0.15.sp,
                        )
                    )
                    Text(
                        text = "${playlist.videos.size} Videos${if (playlist.resources.isNotEmpty()) " & ${playlist.resources.size} Resources" else ""}",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            color = Color(0xFF8798AD),
                            letterSpacing = 0.4.sp,
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.play
                    ),
                    contentDescription = "Playlist Icon"
                )
            }
            if (showPlaylist) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    playlist.videos.mapIndexed { i, video ->
                        VideoItem(
                            video = video,
                            playlistIndex = playlistIndex,
                            videoIndex = i,
                            onVideoClicked = onVideoClicked
                        )
                    }
                }
            }
        }
        if (!intractable) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(color = Color(0X00000000))
            ) {

            }
        }
    }
}

@Preview
@Composable
fun PreviewPlaylistItem() {
    PlaylistItem(
        playlistIndex = 0,
        playlist = PlaylistModel(
            title = "Intro to Modern History",
            videos = listOf(
                VideoModel(
                    title = "History Lesson 1",
                    videoLength = 324,
                    videoLink = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                    isViewable = false,
                ),
                VideoModel(
                    title = "History Lesson 2",
                    videoLength = 324,
                    videoLink = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                    isViewable = false,
                )
            )
        )
    ) { _, _ -> }
}