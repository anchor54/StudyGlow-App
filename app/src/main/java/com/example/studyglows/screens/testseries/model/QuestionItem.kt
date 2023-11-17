package com.example.studyglows.screens.testseries.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

enum class QuestionType(val type: String) {
    SINGLE_CHOICE("single_choice"),
    MULTIPLE_CHOICE("multiple_choice"),
    DESCRIPTIVE("descriptive");

    companion object {
        fun getQuestionType(type: String): QuestionType? =
            values().find { it.type == type }
    }
}

class QuestionItem(
    @SerializedName("id") val questionId: String = UUID.randomUUID().toString(),
    @SerializedName("question") val questionText: String,
    @SerializedName("type") val type: String,
    @SerializedName("options") val options: List<String>? = null
)

class AnswerItem(
    @SerializedName("questionId") val questionId: String,
    @SerializedName("questionType") val questionType: String,
    @SerializedName("answer") val answer: String
)