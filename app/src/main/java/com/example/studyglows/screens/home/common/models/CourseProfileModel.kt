package com.example.studyglows.screens.home.common.models

import com.google.gson.annotations.SerializedName

class CourseProfileModel(
    @SerializedName("imageUrl") val imageUrl: String = "",
    @SerializedName("courseTitle") val courseTitle: String = "",
    @SerializedName("originalPrice") val originalPrice: Float = 0F,
    @SerializedName("discountedPrice") val discountedPrice: Float = 0F,
    @SerializedName("brief") val brief: String = "",
    @SerializedName("educators") val educators: List<Educators> = listOf(),
    @SerializedName("faqs") val faqs: List<FAQ> = listOf(),
)

class Educators(
    @SerializedName("name") val educatorName: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("achievements") val achievements: List<String>
)

class FAQ(
    @SerializedName("question") val question: String,
    @SerializedName("answer") val answer: String,
)