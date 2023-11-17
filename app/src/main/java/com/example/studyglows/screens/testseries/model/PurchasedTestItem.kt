package com.example.studyglows.screens.testseries.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class PurchasedTestItem(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("icon") val icon: String,
    @SerializedName("title") val title: String,
    @SerializedName("total_tests") val totalTests: Int,
    @SerializedName("completed_tests") val completedTests: Int
) {
    fun getProgress(): Float = completedTests.toFloat() / totalTests.toFloat()
}