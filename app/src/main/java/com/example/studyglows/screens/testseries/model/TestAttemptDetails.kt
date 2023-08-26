package com.example.studyglows.screens.testseries.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class TestAttemptDetails(
    @SerializedName("id") val testId: String = UUID.randomUUID().toString(),
    @SerializedName("icon") val icon: String,
    @SerializedName("title") val title: String,
    @SerializedName("total_marks") val totalMarks: Int,
    @SerializedName("obtained_marks") val obtainedMarks: Int,
    @SerializedName("attempted_date") val date: Long,
    @SerializedName("has_result") val hasResult: Boolean
)