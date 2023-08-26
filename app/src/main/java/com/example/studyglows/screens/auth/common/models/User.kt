package com.example.studyglows.screens.auth.common.models

data class User(
    val address: String = "",
    val city: String = "",
    val country: String = "",
    val email: String = "",
    val full_name: String = "",
    val id: String,
    val phone: String = "",
    val profile_image: String = "",
    val state: String = ""
)