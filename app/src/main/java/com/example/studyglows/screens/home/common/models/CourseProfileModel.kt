package com.example.studyglows.screens.home.common.models

import com.google.gson.annotations.SerializedName

class CourseProfileModel(
    @SerializedName("courseId") val courseId: String = "",
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("courseTitle") val courseTitle: String? = null,
    @SerializedName("originalPrice") val originalPrice: Float? = null,
    @SerializedName("discountedPrice") val discountedPrice: Float? = null,
    @SerializedName("brief") val brief: String? = null,
    @SerializedName("features") val features: List<CourseFeature>? = null,
    @SerializedName("educators") val educators: List<Educators>? = null,
    @SerializedName("faqs") val faqs: List<FAQ>? = null,
)

class Educators(
    @SerializedName("name") val educatorName: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("bio") val achievements: String
)

class FAQ(
    @SerializedName("question") val question: String,
    @SerializedName("answer") val answer: String,
)

class CourseFeature(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String
)