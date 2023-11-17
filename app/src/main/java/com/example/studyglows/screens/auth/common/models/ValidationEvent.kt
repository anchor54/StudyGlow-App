package com.example.studyglows.screens.auth.common.models

sealed class ValidationEvent() {
    class Loading(val start: Boolean): ValidationEvent()
    class OTPSentSuccess : ValidationEvent()
    class OTPSentError(val message: String): ValidationEvent()
    class OTPVerifySuccess: ValidationEvent()
    class OTPVerifyError(val message: String): ValidationEvent()
    class NoEvent: ValidationEvent()
}
