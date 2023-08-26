package com.example.studyglows.screens.testseries.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class TestDetailsModule(
    @SerializedName("test_id") val testId: String = UUID.randomUUID().toString(),
    @SerializedName("title") val title: String = "",
    @SerializedName("icon") val icon: String = "",
    @SerializedName("full_tests") val fullTests: List<TestItem> = listOf(),
    @SerializedName("previous_papers") val prevPapers: List<TestItem> = listOf(),
    @SerializedName("chapter_tests") val chapterTests: List<TestItem> = listOf(),
    @SerializedName("section_tests") val sectionTests: List<TestItem> = listOf(),
)