package com.example.studyglows.screens.login.models

data class OTPRequest(
    val country_code: String,
    val phone: String,
    val resend: Boolean
)