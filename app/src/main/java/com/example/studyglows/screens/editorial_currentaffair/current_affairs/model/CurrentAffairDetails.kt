package com.example.studyglows.screens.editorial_currentaffair.current_affairs.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class CurrentAffairDetails(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("title") val title: String = "",
    @SerializedName("image") val image: String = "",
    @SerializedName("contents") val contents: List<String> = listOf()
)