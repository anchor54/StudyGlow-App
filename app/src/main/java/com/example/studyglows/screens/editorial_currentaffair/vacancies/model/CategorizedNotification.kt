package com.example.studyglows.screens.editorial_currentaffair.vacancies.model

import com.google.gson.annotations.SerializedName

class CategorizedNotification(
    @SerializedName("title") val title: String = "",
    @SerializedName("notifications") val notifications: List<NotificationItem> = listOf()
)