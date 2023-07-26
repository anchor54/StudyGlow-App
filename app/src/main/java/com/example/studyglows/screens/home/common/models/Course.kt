package com.example.studyglows.screens.home.common.models

import com.google.gson.annotations.SerializedName

class Course(
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("originalPrice") val originalPrice: Float?,
    @SerializedName("discountedPrice") val discountedPrice: Float?,
    @SerializedName("isBought") val isBought: Boolean?,
    @SerializedName("tag") val tag: String?
)