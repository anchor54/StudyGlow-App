package com.example.studyglows.screens.editorial_currentaffair.editorial.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.UUID

class EditorialItem(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("image") val image: String,
    @SerializedName("title") val title: String,
    @SerializedName("date") val date: Long,
): Serializable