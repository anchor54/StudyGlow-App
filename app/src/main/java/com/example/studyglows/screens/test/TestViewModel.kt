package com.example.studyglows.screens.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.TestSeriesApis
import com.example.studyglows.screens.auth.common.models.TestSeriesUIEvent
import com.example.studyglows.screens.auth.common.models.TestUIEvent
import com.example.studyglows.screens.testseries.model.AnswerItem
import com.example.studyglows.screens.testseries.model.QuestionItem
import com.example.studyglows.screens.testseries.model.QuestionState
import com.example.studyglows.screens.testseries.model.QuestionType
import com.example.studyglows.screens.testseries.model.TestDetailsModule
import com.example.studyglows.screens.testseries.model.TestResultModel
import com.example.studyglows.shared.model.CategorizedMap
import com.example.studyglows.shared.model.SearchResultItem
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
class TestViewModel @Inject constructor(
    private val testSeriesApis: TestSeriesApis
): ViewModel() {

    private val _testDetails = MutableStateFlow(TestDetailsModule())
    val testDetails = _testDetails.asStateFlow()

    private val _questionState = MutableStateFlow(CategorizedMap<String, QuestionState>())
    val questionState = _questionState.asStateFlow()

    private val _questions = MutableStateFlow(listOf<QuestionItem?>())
    val questions = _questions.asStateFlow()

    private val _uiEvent = MutableSharedFlow<TestUIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _currentCategoryIdx = MutableStateFlow(0)
    val currentCategoryIdx = _currentCategoryIdx.asStateFlow()

    private val _currentQuestionIdxs = MutableStateFlow(listOf<Int>())
    val currentQuestionIdxs = _currentQuestionIdxs.asStateFlow()

    private val _categories = MutableStateFlow(listOf<String>())
    val categories = _categories.asStateFlow()

    private val _testResult = MutableStateFlow<TestResultModel?>(null)
    val testResult = _testResult.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _searchResults = MutableStateFlow(listOf<SearchResultItem>())
    val searchResult = _searchResults.asStateFlow()

    fun sendUIEvent(event: TestUIEvent) {
        viewModelScope.launch(Dispatchers.IO) { _uiEvent.emit(event) }
    }

    fun getTestDetails(testId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = testSeriesApis.fetchTestDetails(testId)
            _loading.value = false
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
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getCategoryQuestions(categoryIdx: Int) {
        val category = _categories.value[categoryIdx]
        viewModelScope.launch {
            _loading.value = true
            val deferredRequests = _testDetails.value.questions.getListForCategory(category)
                .map { questionId ->
                    async { testSeriesApis.fetchQuestionDetails(questionId) }
                }

            _questions.value = deferredRequests
                .awaitAll()
                .map { if (it.isSuccessful) it.body() else null }
            _loading.value = false
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