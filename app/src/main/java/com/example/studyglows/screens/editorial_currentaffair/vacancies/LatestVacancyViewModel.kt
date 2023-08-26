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

    fun fetchJobNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = vacancyApis.getJobAlertsAndUpdates()
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
            }
        }
    }

    fun fetchAdmitCardNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = vacancyApis.getAdmitCardAlertsAndUpdates()
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
            }
        }
    }

    fun fetchResultNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = vacancyApis.getResultAlertsAndUpdates()
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
            }
        }
    }
}