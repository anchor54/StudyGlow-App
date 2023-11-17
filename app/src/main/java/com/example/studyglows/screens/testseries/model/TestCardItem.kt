package com.example.studyglows.screens.testseries.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class TestCardItem(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("icon") val icon: String,
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("languages") val languages: List<String>,
    @SerializedName("total_tests") val totalTests: Int,
    @SerializedName("free_tests") val freeTests: Int,
    @SerializedName("price") val price: Float,
)