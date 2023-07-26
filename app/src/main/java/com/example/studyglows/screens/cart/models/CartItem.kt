package com.example.studyglows.screens.cart.models

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class CartItem(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("courseName") val courseName: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("originalPrice") val originalPrice: Float,
    @SerializedName("discountedPrice") val discountedPrice: Float
)