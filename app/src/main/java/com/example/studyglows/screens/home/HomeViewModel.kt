package com.example.studyglows.screens.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.studyglows.repository.CourseRepository
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.cart.models.CartItemModel
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.screens.home.common.models.CourseProfileModel
import com.example.studyglows.screens.home.common.models.OngoingCourse
import com.example.studyglows.screens.home.common.models.PlaylistModel
import com.example.studyglows.screens.home.common.models.PopularSubjects
import com.example.studyglows.screens.home.common.models.ResourceModel
import com.example.studyglows.screens.home.common.models.SubjectwiseCourse
import com.example.studyglows.screens.home.common.models.VideoModel
import com.example.studyglows.shared.model.CategorizedList
import com.example.studyglows.shared.model.SearchResultItem
import com.example.studyglows.utils.Utils.asStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val courseRepository: CourseRepository
): ViewModel() {
    val playlist = courseRepository.chapterResourceLD.map {
        it.map {
            PlaylistModel(
                title = it.first,
                videos = it.second.filter { resource ->
                    resource.type?.toLowerCase() == "video"
                }.map { resource ->
                    VideoModel(
                        title = resource.name ?: "",
                        videoLength = resource.duration ?: 0L,
                        videoLink = resource.url ?: "",
                        isViewable = true
                    )
                },
                resources = it.second.filter { resource ->
                    resource.type?.toLowerCase() != "video"
                }.map { resource ->
                    ResourceModel(
                        title = resource.name ?: "",
                        resourceLink = resource.url ?: ""
                    )
                }
            )
        }
    }.asStateFlow(listOf())

    private val _currentlyWatching = MutableStateFlow(listOf<OngoingCourse>())
    val currentlyWatching = _currentlyWatching.asStateFlow()

    val popularCourses = courseRepository.popularCoursesLD.map {
        it.map { course -> Course(
            course.courseId,
            course.imageUrl ?: "",
            course.title ?: "",
            course.originalPrice ?: 0f,
            course.discountedPrice ?: 0f,
            false,
            "Popular"
        ) }
    }.asStateFlow(listOf())

    val popularCoursesBySubjects = courseRepository.categoryCourseMapLD.map {
        PopularSubjects(
            it.entries.map { entry ->
                SubjectwiseCourse(
                    entry.key,
                    entry.value.map { course ->
                        Course(
                            course.courseId,
                            course.imageUrl ?: "",
                            course.title ?: "",
                            course.originalPrice ?: 0f,
                            course.discountedPrice ?: 0f,
                            false,
                            "Popular"
                        )
                    }
                )
            }
        )
    }.asStateFlow(PopularSubjects(listOf()))

    val subjectSpecificCourses = courseRepository.coursesBySubcategoryLD.map {
        SubjectwiseCourse(
            it.first,
            it.second.map { course -> Course(
                course.courseId,
                course.imageUrl ?: "",
                course.title ?: "",
                course.originalPrice ?: 0f,
                course.discountedPrice ?: 0f,
                false,
                "Popular"
            ) }
        )
    }.asStateFlow(SubjectwiseCourse("", listOf()))

    private val _courseId = MutableStateFlow("")
    val courseId = _courseId.asStateFlow()

    val courseProfile = courseRepository.courseDetailsLD.asStateFlow(CourseProfileModel())

    val similarCourses = courseRepository.similarCoursesLD.map {
        it.map { course -> Course(
            course.courseId,
            course.imageUrl ?: "",
            course.title ?: "",
            course.originalPrice ?: 0f,
            course.discountedPrice ?: 0f,
            false,
            "Popular"
        ) }
    }.asStateFlow(listOf())

    private val _uiEvent = MutableSharedFlow<HomeUIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    val filters = courseRepository.categorizedFilters.map { CategorizedList(it) }.asStateFlow(
        CategorizedList()
    )

    private val _selectedFilters = MutableStateFlow(CategorizedList<String>())
    val selectedFilters = _selectedFilters.asStateFlow()

    private val _savedCourses = MutableStateFlow<List<CartItemModel>>(listOf())
    val savedCourses = _savedCourses.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    val searchResult = courseRepository.filteredCoursesLD.map {
        it.map { course ->
            SearchResultItem(course.courseId, course.title ?: "")
        }
    }.asStateFlow(listOf())

    val filteredCourses = courseRepository.filteredCoursesLD.map {
        it.map { course -> Course(
            course.courseId,
            course.imageUrl ?: "",
            course.title ?: "",
            course.originalPrice ?: 0f,
            course.discountedPrice ?: 0f,
            false,
            "Popular"
        ) }
    }.asStateFlow(listOf())

    val courses = courseRepository.courseLD.asStateFlow(listOf())

    fun triggerEvent(event: HomeUIEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.emit(event)
        }
    }

    init {
        getAllCourses()
    }

    private fun getAllCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            courseRepository.getAllCourses()
            _loading.value = false
        }
    }

    fun getCurrentlyWatchingCourses() {
        viewModelScope.launch(Dispatchers.IO) {
//            _loading.value = true
//            val response = repository.getCurrentlyWatchingCourses()
//            _loading.value = false
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    _currentlyWatching.value = it
//                }
//            } else {
//                _error.value = response.message() ?: "Something went wrong"
//            }
        }
    }

    fun getPopularCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            courseRepository.getPopularCourses()
            _loading.value = false
        }
    }

    fun getTopCoursesOfPopularSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            courseRepository.getPopularCoursesBySubCategories(listOf("CLAT", "Personality Development"))
            _loading.value = false
        }
    }

    fun getAllCoursesForSubject(subjectName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            courseRepository.getAllCoursesBySubCategory(subjectName)
            _loading.value = false
        }
    }

    suspend fun getPlaylist(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            courseRepository.getChaptersForCourse(courseId)
            _loading.value = false
        }
    }

    fun fetchCourseDetails(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            courseRepository.getCourseDetails(courseId)
            _loading.value = false
        }
    }

    fun fetchSimilarCourses(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            courseRepository.getSimilarCourses(courseId)
            _loading.value = false
        }
    }

    fun addToCart(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            courseRepository.addCourseToCart(courseId)
            _loading.value = false
//            if (response.isSuccessful) {
//                triggerEvent(HomeUIEvent.AddToCartSuccess())
//            } else {
//                triggerEvent(HomeUIEvent.AddToCartFailed())
//                _error.value = response.message() ?: "Something went wrong"
//            }
        }
    }

    fun getAllCategoryFilters() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            courseRepository.getAllCategoryFilters()
            _loading.value = false
        }
    }

    fun applyFilters(filters: CategorizedList<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            courseRepository.getFilteredCourses(
                filters.getListForCategory("Category"),
                filters.getListForCategory("Subject"),
                filters.getListForCategory("Faculty")
            )
            _loading.value = false
        }
    }

    fun getAllSavedCourses() {
        viewModelScope.launch(Dispatchers.IO) {
//            _loading.value = true
//            val response = repository.getSavedCourses()
//            _loading.value = false
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    _savedCourses.value = it
//                }
//            } else {
//                _error.value = response.message() ?: "Something went wrong"
//            }
        }
    }

    fun addSavedCourseToCart(courseId: String) {
        _savedCourses.value = _savedCourses.value.filter { it.id != courseId }
        addToCart(courseId)
    }

    fun getSearchResults(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            courseRepository.getSearchedCourses(searchText)
        }
    }
}