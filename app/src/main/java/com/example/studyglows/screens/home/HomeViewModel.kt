package com.example.studyglows.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.CourseApis
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.screens.home.common.models.OngoingCourse
import com.example.studyglows.screens.home.common.models.PopularSubjects
import com.example.studyglows.screens.home.common.models.SubjectwiseCourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
): ViewModel() {
    private val courseApis = CourseApis()

    private val _currentlyWatching = MutableStateFlow(listOf<OngoingCourse>())
    val currentlyWatching = _currentlyWatching.asStateFlow()

    private val _popularCourses = MutableStateFlow(listOf<Course>())
    val popularCourses = _popularCourses.asStateFlow()

    private val _popularCoursesBySubjects = MutableStateFlow(PopularSubjects(listOf()))
    val popularCoursesBySubjects  = _popularCoursesBySubjects.asStateFlow()

    private val _subjectSpecificCourses = MutableStateFlow(SubjectwiseCourse("", listOf()))
    val subjectSpecificCourses  = _subjectSpecificCourses.asStateFlow()

    fun getCurrentlyWatchingCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
            val response = courseApis.getCurrentlyWatchingCourses()
            if (response.isSuccessful) {
                response.body()?.let {
                    _currentlyWatching.value = it
                }
            }
        }
    }

    fun getPopularCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = courseApis.getMostPopularCourses()
            if (response.isSuccessful) {
                response.body()?.let {
                    _popularCourses.value = it
                }
            }
        }
    }

    fun getTopCoursesOfPopularSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = courseApis.getPopularSubjectCourses()
            if (response.isSuccessful) {
                response.body()?.let {
                    _popularCoursesBySubjects.value = it
                }
            }
        }
    }

    fun getAllCoursesForSubject(subjectName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = courseApis.getAllCoursesOfSubject(subjectName)
            if (response.isSuccessful) {
                response.body()?.let {
                    _subjectSpecificCourses.value = it
                }
            }
        }
    }
}