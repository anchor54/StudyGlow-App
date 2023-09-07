package com.example.studyglows.screens.testseries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.TestSeriesApis
import com.example.studyglows.screens.auth.common.models.TestSeriesUIEvent
import com.example.studyglows.screens.testseries.model.AnswerItem
import com.example.studyglows.screens.testseries.model.ExamCategory
import com.example.studyglows.screens.testseries.model.FreeMockTestModule
import com.example.studyglows.screens.testseries.model.PurchasedTestItem
import com.example.studyglows.screens.testseries.model.QuestionItem
import com.example.studyglows.screens.testseries.model.QuestionState
import com.example.studyglows.screens.testseries.model.QuestionType
import com.example.studyglows.screens.testseries.model.SavedTestItemModel
import com.example.studyglows.screens.testseries.model.TestAttemptDetails
import com.example.studyglows.screens.testseries.model.TestCardItem
import com.example.studyglows.screens.testseries.model.TestDetailsModule
import com.example.studyglows.screens.testseries.model.TestResultModel
import com.example.studyglows.screens.testseries.model.TestSeriesDetailsModule
import com.example.studyglows.shared.model.CategorizedMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

    private val _testDetails = MutableStateFlow(TestDetailsModule())
    val testDetails = _testDetails.asStateFlow()

    private val _questionState = MutableStateFlow(CategorizedMap<String, QuestionState>())
    val questionState = _questionState.asStateFlow()

    private val _questions = MutableStateFlow(listOf<QuestionItem?>())
    val questions = _questions.asStateFlow()

    private val _uiEvent = MutableSharedFlow<TestSeriesUIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _currentCategoryIdx = MutableStateFlow(0)
    val currentCategoryIdx = _currentCategoryIdx.asStateFlow()

    private val _currentQuestionIdxs = MutableStateFlow(listOf<Int>())
    val currentQuestionIdxs = _currentQuestionIdxs.asStateFlow()

    private val _categories = MutableStateFlow(listOf<String>())
    val categories = _categories.asStateFlow()

    private val _testResult = MutableStateFlow<TestResultModel?>(null)
    val testResult = _testResult.asStateFlow()

    fun sendUIEvent(event: TestSeriesUIEvent) {
        viewModelScope.launch(Dispatchers.IO) { _uiEvent.emit(event) }
    }

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

    fun getTestSeriesDetails(testId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.fetchTestSeriesDetails(testId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _testSeriesDetails.value = it
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

    fun getTestDetails(testId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.fetchTestDetails(testId)
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    _testDetails.value = data
                    data.questions.apply {
                        _currentQuestionIdxs.value = getAllCategories().map { 0 }
                        _categories.value = getAllCategories()
                        _questionState.value = CategorizedMap(
                            getAllCategories().associateWith { category ->
                                getListForCategory(category).associateWith {
                                    QuestionState.UnseenQuestion
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    fun getCategoryQuestions(categoryIdx: Int) {
        val category = _categories.value[categoryIdx]
        viewModelScope.launch {
            val deferredRequests = _testDetails.value.questions.getListForCategory(category)
                .map { questionId ->
                    async { testSeriesApis.fetchQuestionDetails(questionId) }
                }

            _questions.value = deferredRequests
                .awaitAll()
                .map { if (it.isSuccessful) it.body() else null }
        }
    }

    suspend fun getQuestionDetails(category: String, idx: Int) =
        testSeriesApis.fetchQuestionDetails(_testDetails.value.questions.getListForCategory(category)[idx])

    fun changeCategory(idx: Int) {
        _currentCategoryIdx.value = idx
    }

    fun changeQuestion(questionIdx: Int) {
        val currentQuesIdx = _currentQuestionIdxs.value[_currentCategoryIdx.value]
        val category = _categories.value[_currentCategoryIdx.value]
        val (questionId, questionState) = _questionState.value.getMapForCategory(category).map { it }[currentQuesIdx]
        if (questionState is QuestionState.UnseenQuestion) {
            _questionState.value = _questionState.value.modifyOrAddItem(
                category,
                questionId,
                QuestionState.UnAttemptedQuestion
            )
        }
        val updatedMap = _currentQuestionIdxs.value.toMutableList().apply {
            this[_currentCategoryIdx.value] = questionIdx
        }
        _currentQuestionIdxs.value = updatedMap
    }

    fun addAnswer(questionId: String, answer: String?, questionType: QuestionType?) {
        val category = _questionState.value.getAllCategories()[_currentCategoryIdx.value]
        questionType?.let {
            _questionState.value = _questionState.value.modifyOrAddItem(
                category,
                questionId,
                if (answer != null) {
                    QuestionState.AttemptedQuestion(
                        AnswerItem(
                            questionId = questionId,
                            answer = answer,
                            questionType = questionType.type
                        )
                    )
                } else {
                    QuestionState.UnAttemptedQuestion
                }
            )
        }
    }

    fun submitTest() {}

    fun getTestResult(testId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = testSeriesApis.fetchTestResultDetails(testId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _testResult.value = it
                }
            }
        }
    }
}