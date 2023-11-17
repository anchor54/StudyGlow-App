package com.example.studyglows.screens.testseries.model

import com.example.studyglows.shared.model.CategorizedMap
import com.google.gson.annotations.SerializedName

class TestResultModel(
    @SerializedName("rank") val userRank: Int,
    @SerializedName("total_rankers") val totalRankers: Int,
    @SerializedName("score") val score: Int,
    @SerializedName("total_score") val totalScore: Int,
    @SerializedName("attempted") val attempted: Int,
    @SerializedName("total_questions") val totalQuestions: Int,
    @SerializedName("question_status") private val questionStatus: List<CategoryResult>
) {
    val categorizedStatus = CategorizedMap(
        questionStatus.associate {
            it.category to it.statusMap.map {
                it.key to QuestionResultStatus.from(it.value)
            }.toMap()
        }
    )
}

enum class QuestionResultStatus(val type: String) {
    UNATTEMPTED("unattempted"),
    CORRECT("correct"),
    WRONG("wrong"),
    UNSEEN("unseen");

    companion object {
        fun from(type: String): QuestionResultStatus? =
            values().find { it.type == type }
    }
}

class CategoryResult(
    @SerializedName("category") val category: String,
    @SerializedName("status_map") val statusMap: Map<String, String>
)