package com.example.studyglows.screens.login.models

data class OTPResponse(
    val message: String,
    val new_user: Boolean,
    val resend: Boolean
)