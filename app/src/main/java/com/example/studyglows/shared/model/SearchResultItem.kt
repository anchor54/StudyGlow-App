package com.example.studyglows.shared.model

import com.google.gson.annotations.SerializedName

class SearchResultItem(
    @SerializedName("id") val id: String,
    @SerializedName("result_txt") val text: String,
)