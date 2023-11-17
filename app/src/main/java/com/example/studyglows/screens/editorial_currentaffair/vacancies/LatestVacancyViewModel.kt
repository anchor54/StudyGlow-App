package com.example.studyglows.screens.editorial_currentaffair.vacancies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.LatestVacancyApis
import com.example.studyglows.screens.editorial_currentaffair.vacancies.model.CategorizedNotification
import com.example.studyglows.screens.editorial_currentaffair.vacancies.model.NotificationItem
import com.example.studyglows.screens.editorial_currentaffair.vacancies.model.NotificationType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestVacancyViewModel @Inject constructor(
    private val vacancyApis: LatestVacancyApis
): ViewModel() {
    private val _jobNotifications = MutableStateFlow(CategorizedNotification())
    val jobNotifications = _jobNotifications.asStateFlow()

    private val _admitNotifications = MutableStateFlow(CategorizedNotification())
    val admitNotifications = _admitNotifications.asStateFlow()

    private val _resultNotifications = MutableStateFlow(CategorizedNotification())
    val resultNotifications = _resultNotifications.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    fun fetchJobNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = vacancyApis.getJobAlertsAndUpdates()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let { notifications ->
                    _jobNotifications.value = CategorizedNotification(
                        title = NotificationType.JOB.name,
                        notifications = notifications.map {
                            NotificationItem(
                                type = NotificationType.JOB,
                                tag = it.tag,
                                title = it.title,
                                date = it.date
                            )
                        }
                    )
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun fetchAdmitCardNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = vacancyApis.getAdmitCardAlertsAndUpdates()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let { notifications ->
                    _admitNotifications.value = CategorizedNotification(
                        title = NotificationType.ADMIT_CARD.name,
                        notifications = notifications.map {
                            NotificationItem(
                                type = NotificationType.ADMIT_CARD,
                                tag = it.tag,
                                title = it.title,
                                date = it.date
                            )
                        }
                    )
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun fetchResultNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = vacancyApis.getResultAlertsAndUpdates()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let { notifications ->
                    _resultNotifications.value = CategorizedNotification(
                        title = NotificationType.RESULT.name,
                        notifications = notifications.map {
                            NotificationItem(
                                type = NotificationType.RESULT,
                                tag = it.tag,
                                title = it.title,
                                date = it.date
                            )
                        }
                    )
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }
}