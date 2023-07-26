package com.example.studyglows.screens.home.common.components

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.util.Util
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import com.example.studyglows.screens.home.common.models.VideoModel
import com.example.studyglows.screens.home.common.models.getMediaItems

@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    currentlyPlaying: Int,
    videos: List<VideoModel> = listOf()
) {
    val context = LocalContext.current
    val videoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItems(videos.getMediaItems())
            prepare()
            playWhenReady = true
        }
    }

    LaunchedEffect(key1 = currentlyPlaying) {
        videoPlayer.seekTo(currentlyPlaying, C.TIME_UNSET)
    }

    DisposableEffect(
        AndroidView(
            modifier = modifier,
            factory = {
                PlayerView(it).apply {
                    player = videoPlayer
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            }
        )
    ) {
        onDispose {
            videoPlayer.stop()
            videoPlayer.release()
        }
    }
}

@Preview
@Composable
fun PreviewVideoPlayer() {
    VideoPlayer(
        currentlyPlaying = 0,
        videos = listOf(
            VideoModel(
                title = "History Lesson 1",
                videoLength = 324,
                videoLink = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            )
        )
    )
}