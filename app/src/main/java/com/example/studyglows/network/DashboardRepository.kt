package com.example.studyglows.network

import com.example.studyglows.network.apis.CartApis
import com.example.studyglows.network.apis.CourseApis
import com.example.studyglows.network.apis.LectureApis
import com.example.studyglows.network.apis.UserApis
import com.example.studyglows.shared.model.CategorizedList
import com.example.studyglows.shared.model.CategoryFilter
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val lectureApis: LectureApis,
    private val courseApis: CourseApis,
    private val cartApis: CartApis,
) {
    suspend fun getCurrentlyWatchingCourses() = courseApis.getCurrentlyWatchingCourses()
    suspend fun getMostPopularCourses() = courseApis.getMostPopularCourses()
    suspend fun getPopularSubjectCourses() = courseApis.getPopularSubjectCourses()
    suspend fun getAllCoursesOfSubject(subjectId: String) = courseApis.getAllCoursesOfSubject(subjectId)
    suspend fun getPlaylistsForCourse(courseId: String) = lectureApis.getPlaylistsForCourse(courseId)
    suspend fun getCourseDetails(courseId: String) = courseApis.getCourseDetails(courseId)
    suspend fun getSimilarCourses(courseId: String) = courseApis.getSimilarCourses(courseId)
    suspend fun addCourseToCart(courseId: String) = cartApis.addCourseToCart(courseId)
    suspend fun getAllCategoryFilters() = courseApis.getAllCourseCategoryFilters()
    suspend fun filterCourses(filters: CategorizedList<String>) = courseApis.getFilteredCourses(filters)
    suspend fun getSavedCourses() = cartApis.getSavedCourses()
    suspend fun search(text: String) = courseApis.search(text)
}