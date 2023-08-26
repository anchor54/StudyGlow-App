package com.example.studyglows.screens.auth.common.models

sealed class AuthUIEvent {
    class PhoneNumberChanged(val phoneNumber: String): AuthUIEvent()
    class OTPChanged(val otp: String): AuthUIEvent()
    class OTPSend(): AuthUIEvent()
    class OTPResend(): AuthUIEvent()
    class OTPSubmit(): AuthUIEvent()
}

sealed class HomeUIEvent {
    class AddToCartSuccess: HomeUIEvent()
    class AddToCartFailed: HomeUIEvent()
    class NavigateCourseDetails(val courseId: String): HomeUIEvent()
    class NavigateExploreCourses: HomeUIEvent()
    class ShowFilters: HomeUIEvent()
}

sealed class TestUIEvent {
    class AddToCartSuccess: HomeUIEvent()
    class AddToCartFailed: HomeUIEvent()
}

sealed class AppUIEvent {
    class ShowDrawer(): AppUIEvent()
    class HideDrawer(): AppUIEvent()
}