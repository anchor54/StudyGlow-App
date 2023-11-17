package com.example.studyglows.shared.model

data class CategoryFilter(
    val filterCategory: String = "",
    val filterFields: List<String> = listOf(),
)