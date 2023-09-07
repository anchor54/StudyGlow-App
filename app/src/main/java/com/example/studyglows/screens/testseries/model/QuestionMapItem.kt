package com.example.studyglows.screens.testseries.model

import java.util.UUID

enum class QuestionStatusType(val type: String) {
    REVIEW("review"),
    ATTEMPTED("attempted"),
    UNATTEMPTED("unattempted"),
    UNSEEN("unseen")
}

class QuestionMapItem(
    val index: Int,
    val questionId: String = UUID.randomUUID().toString(),
    val type: String = QuestionStatusType.UNSEEN.type
)