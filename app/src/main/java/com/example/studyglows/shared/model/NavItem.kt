package com.example.studyglows.shared.model

import com.google.gson.annotations.SerializedName

class NavItem(
    @SerializedName("screen") val screen: String = "",
    @SerializedName("params") val params: List<String> = listOf()
)