package com.example.studyglows.screens.editorial_currentaffair.editorial.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class EditorialDetails(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("image") val image: String = "",
    @SerializedName("date") val date: Long = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("subtitle") val subtitle: String = "",
    @SerializedName("author") val author: List<Author> = listOf(),
    @SerializedName("content") val content: String = ""
)

class Author(
    @SerializedName("image") val image: String? = null,
    @SerializedName("name") val name: String = ""
)