package com.example.studyglows.screens.auth.common.models

data class VerifyOTPRequest(
    val country_code: String,
    val email: String,
    val phone: String
)