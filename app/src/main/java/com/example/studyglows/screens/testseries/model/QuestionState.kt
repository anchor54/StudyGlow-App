package com.example.studyglows.screens.testseries.model

sealed class QuestionState() {
    class AttemptedQuestion(val answer: AnswerItem): QuestionState()
    object UnAttemptedQuestion : QuestionState()
    object ReviewedQuestion : QuestionState()
    object UnseenQuestion : QuestionState()
}
