package com.example.studyglows.screens.login.models

sealed class UIEvent {
    class PhoneNumberChanged(val phoneNumber: String): UIEvent()
    class OTPChanged(val otp: String): UIEvent()
    class OTPSend(): UIEvent()
    class OTPResend(): UIEvent()
    class Submit(): UIEvent()
}