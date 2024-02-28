package com.example.studyglows.screens.testseries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.TestSeriesApis
import com.example.studyglows.screens.testseries.model.TestResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestResultViewModel @Inject constructor(
    private val testSeriesApis: TestSeriesApis
): ViewModel() {
    private val _testResult = MutableStateFlow<TestResultModel?>(null)
    val testResult = _testResult.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

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
}