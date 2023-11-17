package com.example.studyglows.screens.home.common.models

import com.google.gson.annotations.SerializedName
import java.util.UUID

class Course(
    @SerializedName("courseId") val courseId: String = UUID.randomUUID().toString(),
    @SerializedName("imageUrl") val imageUrl: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("originalPrice") val originalPrice: Float = 0f,
    @SerializedName("discountedPrice") val discountedPrice: Float = 0f,
    @SerializedName("isBought") val isBought: Boolean = false,
    @SerializedName("tag") val tag: String = ""
)