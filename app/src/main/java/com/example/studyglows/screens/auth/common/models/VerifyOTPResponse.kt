package com.example.studyglows.screens.auth.common.models

data class VerifyOTPResponse(
    val access: String,
    val message: String,
    val refresh: String,
    val user: User
)