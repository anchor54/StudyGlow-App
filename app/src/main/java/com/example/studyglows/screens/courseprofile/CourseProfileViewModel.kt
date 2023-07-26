package com.example.studyglows.screens.courseprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.CourseApis
import com.example.studyglows.screens.home.common.models.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CourseProfileViewModel: ViewModel() {
    private val courseRepository = CourseApis()

    private val _courseProfile = MutableStateFlow(CourseProfileModel())
    val courseProfile = _courseProfile.asStateFlow()

    private val _similarCourses = MutableStateFlow(listOf<Course>())
    val similarCourses = _similarCourses.asStateFlow()

    fun fetchCourseDetails(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = courseRepository.getCourseDetails(courseId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _courseProfile.value = it
                }
            }
        }
    }

    fun fetchSimilarCourses(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = courseRepository.getSimilarCourses(courseId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _similarCourses.value = it
                }
            }
        }
    }
}