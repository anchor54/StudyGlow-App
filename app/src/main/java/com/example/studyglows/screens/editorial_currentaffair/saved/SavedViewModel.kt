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

    fun getSavedEditorials() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = savedItemsApis.getSavedEditorials()
            if (response.isSuccessful) {
                response.body()?.let {
                    _savedEditorials.value = it
                }
            }
        }
    }

    fun getSavedJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = savedItemsApis.getSavedJobs()
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
            }
        }
    }
}