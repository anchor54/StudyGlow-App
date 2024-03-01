package com.example.studyglows.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.studyglows.db.StudyGlowsDatabase
import com.example.studyglows.db.models.Cart
import com.example.studyglows.db.models.Category
import com.example.studyglows.db.models.Chapter
import com.example.studyglows.db.models.ChapterResource
import com.example.studyglows.db.models.Course
import com.example.studyglows.db.models.CourseFaculties
import com.example.studyglows.db.models.CourseFeatures
import com.example.studyglows.db.models.CourseWithResourceModel
import com.example.studyglows.db.models.Faculty
import com.example.studyglows.db.models.Feature
import com.example.studyglows.db.models.Resource
import com.example.studyglows.db.models.Subcategory
import com.example.studyglows.network.apis.CourseApiService
import com.example.studyglows.network.models.CartPostRequestBody
import com.example.studyglows.screens.home.common.models.CourseProfileModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val courseApi: CourseApiService,
    private val database: StudyGlowsDatabase
) {
    private val _courseLD = MutableLiveData<List<Course>>()
    val courseLD: LiveData<List<Course>>
        get() = _courseLD

    private val _popularCoursesLD = MutableLiveData<List<CourseWithResourceModel>>()
    val popularCoursesLD: LiveData<List<CourseWithResourceModel>>
        get() = _popularCoursesLD

    private val _similarCoursesLD = MutableLiveData<List<CourseWithResourceModel>>()
    val similarCoursesLD: LiveData<List<CourseWithResourceModel>>
        get() = _similarCoursesLD

    private val _coursesBySubcategoryLD = MutableLiveData<Pair<String, List<CourseWithResourceModel>>>()
    val coursesBySubcategoryLD: LiveData<Pair<String, List<CourseWithResourceModel>>>
        get() = _coursesBySubcategoryLD

    private val _courseDetailsLD = MutableLiveData<CourseProfileModel>()
    val courseDetailsLD: LiveData<CourseProfileModel>
        get() = _courseDetailsLD

    private val _categoryCourseMapLD = MutableLiveData<Map<String, List<CourseWithResourceModel>>>()
    val categoryCourseMapLD: LiveData<Map<String, List<CourseWithResourceModel>>>
        get() = _categoryCourseMapLD

    private val _filteredCoursesLD = MutableLiveData<List<CourseWithResourceModel>>()
    val filteredCoursesLD: LiveData<List<CourseWithResourceModel>>
        get() = _filteredCoursesLD

    private val _categorizedFilters = MutableLiveData<Map<String, List<String>>>()
    val categorizedFilters: LiveData<Map<String, List<String>>>
        get() = _categorizedFilters

    private val _chapterResourcesLD = MutableLiveData<List<Pair<String, List<ChapterResource>>>>()
    val chapterResourceLD: LiveData<List<Pair<String, List<ChapterResource>>>>
        get() = _chapterResourcesLD

    suspend fun getAllCourses() {
        val response = courseApi.getAllCourses()
        if (response.isSuccessful) {
            response.body()?.let { courseList ->
                val courseListDB = mutableListOf<Course>()
                courseList.forEach { course ->
                    database.courseDao().apply {
                        val category = course.category?.let { Category(it.id, it.name) }
                        val resource = course.resource?.let {
                            Resource(it.chapter, it.description, it.duration, it.id, it.name, it.size, it.type, it.url)
                        }
                        val subcategory = course.subcategory?.let { Subcategory(it.category, it.id, it.name) }
                        course.features?.forEach {
                            addFeature(Feature(it.id, it.name))
                        }
                        course.facuties?.forEach {
                            addFaculty(Faculty(it.active, it.bio, it.id, it.joiningDate, it.name, it.resource))
                        }
                        category?.let { addCategory(it) }
                        resource?.let { addResource(it) }
                        subcategory?.let { addSubCategory(it) }
                        courseListDB.add(
                            Course(
                                course.id,
                                course.title,
                                resource?.id,
                                course.about,
                                course.language,
                                course.price,
                                course.mrp,
                                course.startDate,
                                course.purchaseDuration,
                                course.duration,
                                course.productCode,
                                category?.id,
                                subcategory?.id,
                                course.publish
                            )
                        )
                        addCourse(courseListDB.last())
                        course.features?.forEach { addCourseFeatureMap(CourseFeatures(course.id, it.id)) }
                        course.facuties?.forEach { addCourseFacultyMap(CourseFaculties(course.id, it.id)) }
                    }
                }
                _courseLD.postValue(courseListDB)
            }
        }
    }

    suspend fun getPopularCourses() {
        val data = database.courseDao().getPopularCourses()
        _popularCoursesLD.postValue(data)
    }

    suspend fun getSimilarCourses(courseId: String) {
        val data = database.courseDao().getSimilarCourses(courseId.toLong())
        _similarCoursesLD.postValue(data)
    }

    suspend fun getPopularCoursesBySubCategories(subcategories: List<String>) {
        coroutineScope {
            val courses = subcategories.map { async { database.courseDao().getCoursesForSubcategory(it) } }.awaitAll()
            val map = mutableMapOf<String, List<CourseWithResourceModel>>()
            for (i in 1..subcategories.size) {
                map[subcategories[i-1]] = courses[i-1]
            }
            _categoryCourseMapLD.postValue(map)
        }
    }

    suspend fun getAllCoursesBySubCategory(subcategory: String) {
        val data = database.courseDao().getCoursesBySubcategory(subcategory)
        _coursesBySubcategoryLD.postValue(Pair(subcategory, data))
    }

    suspend fun getCourseDetails(courseId: String) {
        database.courseDao().apply {
            val course = getCourseDetails(courseId)
            val features = getCourseFeatures(courseId)
            val educators = getCourseFaculties(courseId)
            _courseDetailsLD.postValue(
                CourseProfileModel(
                    course.courseId,
                    course.imageUrl,
                    course.title,
                    course.originalPrice,
                    course.discountedPrice,
                    course.brief,
                    features,
                    educators,
                    null
                )
            )
        }
    }

    suspend fun getAllCategoryFilters() {
        database.courseDao().apply {
            val categories = getAllCategories()
            val subcategories = getAllSubcategories()
            val educators = getAllEducators()
            val map = mutableMapOf<String, List<String>>()
            if (categories.isNotEmpty()) map["Category"] = categories
            if (subcategories.isNotEmpty()) map["Subject"] = subcategories
            if (educators.isNotEmpty()) map["Faculty"] = educators
            _categorizedFilters.postValue(map)
        }
    }

    suspend fun getFilteredCourses(categories: List<String>? = null, subcategories: List<String>? = null, educators: List<String>? = null) {
        val query = "SELECT courses.id as courseId, course_resource.url as imageUrl, courses.title as courseTitle, courses.mrp as originalPrice, courses.price as discountedPrice, courses.about as brief FROM (SELECT courses.* FROM courses ${if (!categories.isNullOrEmpty()) "INNER JOIN course_category ON course_category.name IN (:categories) AND courses.category_id = course_category.id" else ""} ${if (!subcategories.isNullOrEmpty()) "INNER JOIN course_subcategory ON course_subcategory.name IN (:subcategories) AND courses.subcategory_id = course_subcategory.id" else ""} ${if (!educators.isNullOrEmpty()) "INNER JOIN course_faculties ON course_faculties.course_id = courses.id WHERE course_faculties.faculty_id = faculty.id AND faculty.name IN (:educators)" else ""}) as courses LEFT JOIN course_resource ON courses.resource_id = course_resource.id"
        val args = mutableListOf<String>()
        if (!categories.isNullOrEmpty()) args.add(categories.joinToString(","))
        if (!subcategories.isNullOrEmpty()) args.add(subcategories.joinToString(","))
        if (!educators.isNullOrEmpty()) args.add(educators.joinToString(","))
        val data = database.courseDao().getFilteredCourses(SimpleSQLiteQuery(query, args.toTypedArray()))
        _filteredCoursesLD.postValue(data)
    }

    suspend fun getSearchedCourses(search: String) {
        val data = database.courseDao().searchCourse("%$search%")
        _filteredCoursesLD.postValue(data)
    }

    suspend fun getChaptersForCourse(courseId: String) {
        val response = courseApi.getAllChaptersForCourse(courseId)
        if (response.isSuccessful) {
            response.body()?.let { chapterList ->
                val chapters = chapterList.map { Chapter(it.id, it.course.toLong(), it.name) }
                getResourcesForChapters(chapters.map { it.id.toString() })
                database.courseDao().addChapters(chapters)
            }
        }
    }

    suspend fun markChapterWithStatus(status: String, id: Long) {
        database.courseDao().markResourceAs(status, id)
        _chapterResourcesLD.postValue(
            _chapterResourcesLD.value?.map {
                it.first to it.second.map { resource ->
                    if (resource.id == id) resource.copy(playingStatus = status) else resource
                }
            }
        )
    }

    private suspend fun getResourcesForChapters(chapters: List<String>) {
        coroutineScope {
            val responses = chapters.map { async { courseApi.getAllResourcesForChapter(it) } }.awaitAll()
            val map = responses.filter { it.isSuccessful }.mapNotNull { response ->
                response.body()?.let {
                    val resources = it.map { resource ->
                        ChapterResource(
                            resource.id.toLong(),
                            resource.chapter.id.toLong(),
                            resource.description,
                            resource.duration.toLong(),
                            resource.name,
                            resource.size.toLong(),
                            resource.type,
                            resource.url
                        )
                    }
                    database.courseDao().addChapterResources(resources)
                    if (resources.isNotEmpty()) it[0].chapter.name to resources else null
                }
            }
            _chapterResourcesLD.postValue(map)
        }
    }
}