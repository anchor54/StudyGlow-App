package com.example.studyglows.screens.editorial_currentaffair.vacancies.model

import com.google.gson.annotations.SerializedName

class AlertItemResponse(
    @SerializedName("title") val title: String,
    @SerializedName("tag") val tag: String,
    @SerializedName("date") val date: Long? = null,
)