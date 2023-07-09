package com.example.studyglows.screens.login.models

data class UIState(
    val phoneNumber: String = "",
    val otp: String = "",
    val isPhoneNumberValid: Boolean = false,
    val isOTPValid: Boolean = false
)