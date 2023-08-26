package com.example.studyglows.screens.testseries.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class FreeMockTestModule(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("title") val title: String,
    @SerializedName("max_marks") val maxMarks: Int,
    @SerializedName("questions") val totalQuestions: Int,
    @SerializedName("duration") val duration: Int,
    @SerializedName("expiry_date") val expiresAt: Long
)