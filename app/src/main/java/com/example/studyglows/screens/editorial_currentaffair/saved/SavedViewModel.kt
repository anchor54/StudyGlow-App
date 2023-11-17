package com.example.studyglows.screens.editorial_currentaffair.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.SavedItemsApis
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialItem
import com.example.studyglows.screens.editorial_currentaffair.vacancies.model.NotificationItem
import com.example.studyglows.screens.editorial_currentaffair.vacancies.model.NotificationType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val savedItemsApis: SavedItemsApis
): ViewModel() {
    private val _savedEditorials = MutableStateFlow(listOf<EditorialItem>())
    val savedEditorials = _savedEditorials.asStateFlow()

    private val _savedJobs = MutableStateFlow(listOf<NotificationItem>())
    val savedJobs = _savedJobs.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    fun getSavedEditorials() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = savedItemsApis.getSavedEditorials()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _savedEditorials.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun getSavedJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = savedItemsApis.getSavedJobs()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    _savedJobs.value = res.map {
                        NotificationItem(
                            type = NotificationType.JOB,
                            title = it.title,
                            date = it.date,
                            tag = it.tag
                        )
                    }
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }
}