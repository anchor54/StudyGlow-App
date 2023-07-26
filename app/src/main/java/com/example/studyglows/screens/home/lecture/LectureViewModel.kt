package com.example.studyglows.screens.home.lecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.LectureApis
import com.example.studyglows.screens.home.common.models.PlaylistModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LectureViewModel: ViewModel() {
    private val lectureApi = LectureApis()
    private val _playlist = MutableStateFlow(listOf<PlaylistModel>())
    val playlist = _playlist.asStateFlow()

    suspend fun getPlaylist(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = lectureApi.getPlaylistsForCourse(courseId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _playlist.value = it
                }
            }
        }
    }
}