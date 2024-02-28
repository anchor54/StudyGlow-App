package com.example.studyglows.screens.testseries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.TestSeriesApis
import com.example.studyglows.screens.auth.common.models.TestSeriesUIEvent
import com.example.studyglows.screens.testseries.model.ExamCategory
import com.example.studyglows.screens.testseries.model.FreeMockTestModule
import com.example.studyglows.screens.testseries.model.PurchasedTestItem
import com.example.studyglows.screens.testseries.model.SavedTestItemModel
import com.example.studyglows.screens.testseries.model.TestAttemptDetails
import com.example.studyglows.screens.testseries.model.TestCardItem
import com.example.studyglows.screens.testseries.model.TestResultModel
import com.example.studyglows.screens.testseries.model.TestSeriesDetailsModule
import com.example.studyglows.shared.model.SearchResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestSeriesViewModel @Inject constructor(
    private val testSeriesApis: TestSeriesApis
): ViewModel() {
    private val _testsInProgress = MutableStateFlow(listOf<PurchasedTestItem>())
    val testsInProgress = _testsInProgress.asStateFlow()

    private val _popularTests = MutableStateFlow(listOf<TestCardItem>())
    val popularTests = _popularTests.asStateFlow()

    private val _recommendedTests = MutableStateFlow(listOf<TestCardItem>())
    val recommendedTests = _recommendedTests.asStateFlow()

    private val _freeMocks = MutableStateFlow(listOf<FreeMockTestModule>())
    val freeMocks = _freeMocks.asStateFlow()

    private val _examCategories = MutableStateFlow(listOf<ExamCategory>())
    val examCategories = _examCategories.asStateFlow()

    private val _testSeriesDetails = MutableStateFlow(TestSeriesDetailsModule())
    val testSeriesDetails = _testSeriesDetails.asStateFlow()

    private val _attemptedTests = MutableStateFlow(listOf<TestAttemptDetails>())
    val attemptedTests = _attemptedTests.asStateFlow()

    private val _savedCourses = MutableStateFlow<List<SavedTestItemModel>>(listOf())
    val savedCourses = _savedCourses.asStateFlow()

    private val _uiEvent = MutableSharedFlow<TestSeriesUIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _testResult = MutableStateFlow<TestResultModel?>(null)
    val testResult = _testResult.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _searchResults = MutableStateFlow(listOf<SearchResultItem>())
    val searchResult = _searchResults.asStateFlow()

    fun sendUIEvent(event: TestSeriesUIEvent) {
        viewModelScope.launch(Dispatchers.IO) { _uiEvent.emit(event) }
    }

    fun getCurrentlyAttemptingTests(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.fetchRecentlyAttemptedTests()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _testsInProgress.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getRecommendedTests() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.fetchRecommendedTests()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _recommendedTests.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getPopularTests() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.fetchPopularTests()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _popularTests.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getFreeMockTests() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.fetchFreeMockTests()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _freeMocks.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getExamCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.fetchExamCategories()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _examCategories.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getTestSeriesDetails(testId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.fetchTestSeriesDetails(testId)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _testSeriesDetails.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getAttemptedTests(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.fetchAttemptedTests(userId)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _attemptedTests.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getAllSavedCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.getSavedCourses()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _savedCourses.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun addSavedCourseToCart(courseId: String) {
        _savedCourses.value = _savedCourses.value.filter { it.id != courseId }
        addToCart(courseId)
    }

    fun addToCart(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.addTestToCart(courseId)
            _loading.value = false
            if (response.isSuccessful) {
                // todo: show toast
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getTestResult(testId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.fetchTestResultDetails(testId)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _testResult.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getSearchResults(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.search(searchText)
            if (response.isSuccessful) {
                response.body()?.let {
                    _searchResults.value = it
                }
            }
        }
    }
}