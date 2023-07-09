package com.example.studyglows.screens.auth.common.models

data class OTPRequest(
    val country_code: String,
    val phone: String,
    val resend: Boolean
)