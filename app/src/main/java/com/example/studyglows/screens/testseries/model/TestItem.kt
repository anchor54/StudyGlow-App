package com.example.studyglows.screens.testseries.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class TestItem(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("icon") val icon: String,
    @SerializedName("title") val title: String,
    @SerializedName("questions") val questions: Int,
    @SerializedName("duration") val duration: Int,
    @SerializedName("tag") val tag: String,
    @SerializedName("marks") val marks: Int? = null
)