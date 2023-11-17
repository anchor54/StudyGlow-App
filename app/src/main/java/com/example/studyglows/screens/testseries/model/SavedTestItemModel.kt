package com.example.studyglows.screens.testseries.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class SavedTestItemModel(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("total_tests") val totalTests: Int,
    @SerializedName("original_price") val originalPrice: Float,
    @SerializedName("discounted_price") val discountedPrice: Float
)