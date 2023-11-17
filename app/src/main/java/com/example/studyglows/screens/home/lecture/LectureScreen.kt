package com.example.studyglows.screens.home.lecture

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.common.components.PlaylistItem
import com.example.studyglows.screens.home.common.components.VideoPlayer

@Composable
fun LectureScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    var currVideoIndex by remember { mutableStateOf(0) }
    var currPlaylistIndex by remember { mutableStateOf(0) }
    val playlists by viewModel.playlist.collectAsState()
    val courseId = navHostController.currentBackStackEntry?.arguments?.getString("courseId") ?: ""

    LaunchedEffect(key1 = courseId) {
        viewModel.getPlaylist(courseId)
    }

    val currentlyPlaying by remember {
        derivedStateOf {
            var videos = 0
            for (i in 0 until currPlaylistIndex) {
                videos += playlists[i].videos.size
            }
            videos + currVideoIndex
        }
    }

    Column(modifier = modifier) {
        VideoPlayer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true),
            videos = playlists.flatMap { it.videos },
            currentlyPlaying = currentlyPlaying
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        ) {
            itemsIndexed(playlists) { i, playlist ->
                PlaylistItem(playlist = playlist, playlistIndex = i) { playlistIndex, videoIndex ->
                    currPlaylistIndex = playlistIndex
                    currVideoIndex = videoIndex
                }
            }
        }
    }
}