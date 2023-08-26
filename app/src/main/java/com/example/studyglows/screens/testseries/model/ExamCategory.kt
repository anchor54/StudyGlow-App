package com.example.studyglows.screens.testseries.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class ExamCategory(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("name") val examName: String,
    @SerializedName("icon") val icon: String
)