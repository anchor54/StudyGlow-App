package com.example.studyglows.screens.home.common.models

import com.google.gson.annotations.SerializedName

class OngoingCourse(
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("courseName") val courseName: String?,
    @SerializedName("currentChapter") val currentChapter: String?,
    @SerializedName("progress") val progress: Float?,
)
