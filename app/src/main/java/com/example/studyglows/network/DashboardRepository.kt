package com.example.studyglows.network

import com.example.studyglows.network.apis.CourseApis
import com.example.studyglows.network.apis.LectureApis
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val lectureApis: LectureApis,
    private val courseApis: CourseApis
) {
    suspend fun getCurrentlyWatchingCourses() = courseApis.getCurrentlyWatchingCourses()
    suspend fun getMostPopularCourses() = courseApis.getMostPopularCourses()
    suspend fun getPopularSubjectCourses() = courseApis.getPopularSubjectCourses()
    suspend fun getAllCoursesOfSubject(subjectId: String) = courseApis.getAllCoursesOfSubject(subjectId)
    suspend fun getPlaylistsForCourse(courseId: String) = lectureApis.getPlaylistsForCourse(courseId)
    suspend fun getCourseDetails(courseId: String) = courseApis.getCourseDetails(courseId)
    suspend fun getSimilarCourses(courseId: String) = courseApis.getSimilarCourses(courseId)
    suspend fun addCourseToCart(courseId: String) = courseApis.addCourseToCart(courseId)
}