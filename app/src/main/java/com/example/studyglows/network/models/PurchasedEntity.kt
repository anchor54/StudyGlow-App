package com.example.studyglows.network.models

import com.google.gson.annotations.SerializedName

class PurchasedEntity(
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("purchaseDate") val purchaseDate: Long,
    @SerializedName("validUntil") val validUntil: Long
)