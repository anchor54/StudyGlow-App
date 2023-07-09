package com.example.studyglows.screens.auth.common.models

data class OTPResponse(
    val message: String,
    val new_user: Boolean,
    val resend: Boolean
)