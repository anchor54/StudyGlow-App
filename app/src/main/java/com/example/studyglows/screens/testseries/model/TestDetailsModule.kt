package com.example.studyglows.screens.testseries.model

import com.example.studyglows.shared.model.CategorizedList
import com.google.gson.annotations.SerializedName
import java.util.UUID

class TestDetailsModule(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("test_series") val testSeries: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("test_type") val testType: String = "",
    @SerializedName("questions") val questions: CategorizedList<String> = CategorizedList(),
    @SerializedName("duration") val duration: Long = 0L,
    @SerializedName("total_marks") val totalMarks: Int = 0,
    @SerializedName("description") val description: String = ""
) {
    val totalQuestionCount = questions.getAllCategories().flatMap { questions.getListForCategory(it) }.size
    val categories = questions.size()
    fun categoryQuestionCount(category: String): Int = questions.getListForCategory(category).size
}