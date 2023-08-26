package com.example.studyglows.screens.testseries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.TestSeriesApis
import com.example.studyglows.screens.testseries.model.ExamCategory
import com.example.studyglows.screens.testseries.model.FreeMockTestModule
import com.example.studyglows.screens.testseries.model.PurchasedTestItem
import com.example.studyglows.screens.testseries.model.SavedTestItemModel
import com.example.studyglows.screens.testseries.model.TestAttemptDetails
import com.example.studyglows.screens.testseries.model.TestCardItem
import com.example.studyglows.screens.testseries.model.TestDetailsModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val _testDetails = MutableStateFlow(TestDetailsModule())
    val testDetails = _testDetails.asStateFlow()

    private val _attemptedTests = MutableStateFlow(listOf<TestAttemptDetails>())
    val attemptedTests = _attemptedTests.asStateFlow()

    private val _savedCourses = MutableStateFlow<List<SavedTestItemModel>>(listOf())
    val savedCourses = _savedCourses.asStateFlow()

    fun getCurrentlyAttemptingTests(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.fetchRecentlyAttemptedTests()
            if (response.isSuccessful) {
                response.body()?.let {
                    _testsInProgress.value = it
                }
            }
        }
    }

    fun getRecommendedTests() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.fetchRecommendedTests()
            if (response.isSuccessful) {
                response.body()?.let {
                    _recommendedTests.value = it
                }
            }
        }
    }

    fun getPopularTests() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.fetchPopularTests()
            if (response.isSuccessful) {
                response.body()?.let {
                    _popularTests.value = it
                }
            }
        }
    }

    fun getFreeMockTests() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.fetchFreeMockTests()
            if (response.isSuccessful) {
                response.body()?.let {
                    _freeMocks.value = it
                }
            }
        }
    }

    fun getExamCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.fetchExamCategories()
            if (response.isSuccessful) {
                response.body()?.let {
                    _examCategories.value = it
                }
            }
        }
    }

    fun getTestDetails(testId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.fetchTestDetails(testId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _testDetails.value = it
                }
            }
        }
    }

    fun getAttemptedTests(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.fetchAttemptedTests(userId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _attemptedTests.value = it
                }
            }
        }
    }

    fun getAllSavedCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.getSavedCourses()
            if (response.isSuccessful) {
                response.body()?.let {
                    _savedCourses.value = it
                }
            }
        }
    }

    fun addSavedCourseToCart(courseId: String) {
        _savedCourses.value = _savedCourses.value.filter { it.id != courseId }
        addToCart(courseId)
    }

    fun addToCart(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.addTestToCart(courseId)
//            if (response.isSuccessful) {
//                triggerEvent(TestUIEvent.AddToCartSuccess())
//            } else {
//                triggerEvent(TestUIEvent.AddToCartFailed())
//            }
        }
    }
}