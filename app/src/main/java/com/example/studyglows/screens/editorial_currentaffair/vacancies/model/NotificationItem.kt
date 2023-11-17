package com.example.studyglows.screens.editorial_currentaffair.vacancies.model

import com.google.gson.annotations.SerializedName

enum class NotificationType(name: String) {
    JOB("name"),
    ADMIT_CARD("admit_card"),
    RESULT("result")
}

class NotificationItem(
    @SerializedName("type") val type: NotificationType,
    @SerializedName("title") val title: String,
    @SerializedName("date") val date: Long? = null,
    @SerializedName("tag") val tag: String
)