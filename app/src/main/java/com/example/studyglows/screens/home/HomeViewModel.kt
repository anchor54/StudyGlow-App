package com.example.studyglows.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.DashboardRepository
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.cart.models.CartItemModel
import com.example.studyglows.shared.model.CategoryFilter
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.screens.home.common.models.CourseProfileModel
import com.example.studyglows.screens.home.common.models.OngoingCourse
import com.example.studyglows.screens.home.common.models.PlaylistModel
import com.example.studyglows.screens.home.common.models.PopularSubjects
import com.example.studyglows.screens.home.common.models.SubjectwiseCourse
import com.example.studyglows.shared.model.CategorizedList
import com.example.studyglows.shared.model.SearchResultItem
import com.example.studyglows.utils.Utils.replace
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

    private val _filters = MutableStateFlow(CategorizedList<String>())
    val filters = _filters.asStateFlow()

    private val _selectedFilters = MutableStateFlow(CategorizedList<String>())
    val selectedFilters = _selectedFilters.asStateFlow()

    private val _savedCourses = MutableStateFlow<List<CartItemModel>>(listOf())
    val savedCourses = _savedCourses.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    private val _searchResults = MutableStateFlow(listOf<SearchResultItem>())
    val searchResult = _searchResults.asStateFlow()

    private val _filteredCourses = MutableStateFlow(listOf<Course>())
    val filteredCourses = _filteredCourses.asStateFlow()

    fun triggerEvent(event: HomeUIEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.emit(event)
        }
    }

    fun getCurrentlyWatchingCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getCurrentlyWatchingCourses()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _currentlyWatching.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun getPopularCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getMostPopularCourses()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _popularCourses.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun getTopCoursesOfPopularSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getPopularSubjectCourses()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _popularCoursesBySubjects.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun getAllCoursesForSubject(subjectName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getAllCoursesOfSubject(subjectName)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _subjectSpecificCourses.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    suspend fun getPlaylist(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getPlaylistsForCourse(courseId)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _playlist.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun fetchCourseDetails(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getCourseDetails(courseId)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _courseProfile.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun fetchSimilarCourses(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getSimilarCourses(courseId)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _similarCourses.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun addToCart(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.addCourseToCart(courseId)
            _loading.value = false
            if (response.isSuccessful) {
                triggerEvent(HomeUIEvent.AddToCartSuccess())
            } else {
                triggerEvent(HomeUIEvent.AddToCartFailed())
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun getAllCategoryFilters() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getAllCategoryFilters()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _filters.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun applyFilters(filters: CategorizedList<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.filterCourses(filters)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _filteredCourses.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun getAllSavedCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getSavedCourses()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _savedCourses.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun addSavedCourseToCart(courseId: String) {
        _savedCourses.value = _savedCourses.value.filter { it.id != courseId }
        addToCart(courseId)
    }

    fun getSearchResults(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.search(searchText)
            if (response.isSuccessful) {
                response.body()?.let {
                    _searchResults.value = it
                }
            }
        }
    }
}