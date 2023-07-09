package com.example.studyglows.screens.login.models

data class VerifyOTPRequest(
    val country_code: String,
    val otp: String,
    val phone: String
)