package com.example.studyglows.navigation

sealed class Screen(val route: String) {
    object Welcome: Screen(route = "welcome")
    object Login: Screen(route = "login_screen")
    object Otp: Screen(route = "otp_screen")
}
