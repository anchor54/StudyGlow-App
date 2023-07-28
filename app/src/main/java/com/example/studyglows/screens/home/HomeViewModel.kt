package com.example.studyglows.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.DashboardRepository
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.screens.home.common.models.CourseProfileModel
import com.example.studyglows.screens.home.common.models.OngoingCourse
import com.example.studyglows.screens.home.common.models.PlaylistModel
import com.example.studyglows.screens.home.common.models.PopularSubjects
import com.example.studyglows.screens.home.common.models.SubjectwiseCourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DashboardRepository
): ViewModel() {
    private val _playlist = MutableStateFlow(listOf<PlaylistModel>())
    val playlist = _playlist.asStateFlow()

    private val _currentlyWatching = MutableStateFlow(listOf<OngoingCourse>())
    val currentlyWatching = _currentlyWatching.asStateFlow()

    private val _popularCourses = MutableStateFlow(listOf<Course>())
    val popularCourses = _popularCourses.asStateFlow()

    private val _popularCoursesBySubjects = MutableStateFlow(PopularSubjects(listOf()))
    val popularCoursesBySubjects  = _popularCoursesBySubjects.asStateFlow()

    private val _subjectSpecificCourses = MutableStateFlow(SubjectwiseCourse("", listOf()))
    val subjectSpecificCourses  = _subjectSpecificCourses.asStateFlow()

    private val _courseId = MutableStateFlow("")
    val courseId = _courseId.asStateFlow()

    private val _courseProfile = MutableStateFlow(CourseProfileModel())
    val courseProfile = _courseProfile.asStateFlow()

    private val _similarCourses = MutableStateFlow(listOf<Course>())
    val similarCourses = _similarCourses.asStateFlow()

    private val _uiEvent = MutableSharedFlow<HomeUIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun triggerEvent(event: HomeUIEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.emit(event)
        }
    }

    fun getCurrentlyWatchingCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
            val response = repository.getCurrentlyWatchingCourses()
            if (response.isSuccessful) {
                response.body()?.let {
                    _currentlyWatching.value = it
                }
            }
        }
    }

    fun getPopularCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMostPopularCourses()
            if (response.isSuccessful) {
                response.body()?.let {
                    _popularCourses.value = it
                }
            }
        }
    }

    fun getTopCoursesOfPopularSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPopularSubjectCourses()
            if (response.isSuccessful) {
                response.body()?.let {
                    _popularCoursesBySubjects.value = it
                }
            }
        }
    }

    fun getAllCoursesForSubject(subjectName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllCoursesOfSubject(subjectName)
            if (response.isSuccessful) {
                response.body()?.let {
                    _subjectSpecificCourses.value = it
                }
            }
        }
    }

    suspend fun getPlaylist(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPlaylistsForCourse(courseId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _playlist.value = it
                }
            }
        }
    }

    fun fetchCourseDetails(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCourseDetails(courseId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _courseProfile.value = it
                }
            }
        }
    }

    fun fetchSimilarCourses(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getSimilarCourses(courseId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _similarCourses.value = it
                }
            }
        }
    }

    fun addToCart(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.addCourseToCart(courseId)
            if (response.isSuccessful) {
                triggerEvent(HomeUIEvent.AddToCartSuccess())
            } else {
                triggerEvent(HomeUIEvent.AddToCartFailed())
            }
        }
    }
}