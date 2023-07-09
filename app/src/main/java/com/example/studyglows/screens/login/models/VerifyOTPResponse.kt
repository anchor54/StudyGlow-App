package com.example.studyglows.screens.login.models

data class VerifyOTPResponse(
    val access: String? = null,
    val message: String,
    val refresh: String? = null,
    val user: User? = null
)