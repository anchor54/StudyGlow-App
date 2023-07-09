package com.example.studyglows.screens.auth.common.models

data class VerifyOTPRequest(
    val country_code: String,
    val otp: String,
    val phone: String
)