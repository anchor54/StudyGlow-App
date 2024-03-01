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
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.studyglows.screens.home.common.models.VideoModel
import com.example.studyglows.screens.home.common.models.getMediaItems

@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    currentlyPlaying: Int,
    videos: List<VideoModel> = listOf(),
    duration: Long,
    markVideoAsWatched: () -> Unit
) {
    val context = LocalContext.current
    val videoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItems(videos.getMediaItems())
            prepare()
            playWhenReady = true
            addListener(object : Player.Listener {
                private fun checkRemainingTime() {
                    if (duration <= 0) return
                    val remainingTime = duration - currentPosition
                    val threshold = 10 * 1000 // 10 seconds before the end of the video

                    if (remainingTime <= threshold) {
                        markVideoAsWatched()
                    }
                }

                override fun onPositionDiscontinuity(oldPosition: Player.PositionInfo, newPosition: Player.PositionInfo, reason: Int) {
                    checkRemainingTime()
                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_READY || playbackState == Player.STATE_BUFFERING) {
                        checkRemainingTime()
                    }
                }
            })
        }
    }

    LaunchedEffect(key1 = currentlyPlaying) {
        videoPlayer.seekTo(currentlyPlaying, C.TIME_UNSET)
    }

    LaunchedEffect(key1 = videos) {
        videoPlayer.setMediaItems(videos.getMediaItems())
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