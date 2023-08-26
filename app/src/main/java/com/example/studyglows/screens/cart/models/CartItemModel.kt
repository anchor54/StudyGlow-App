package com.example.studyglows.screens.cart.models

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class CartItemModel(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String? = null,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("originalPrice") val originalPrice: Float,
    @SerializedName("discountedPrice") val discountedPrice: Float
)