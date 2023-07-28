package com.example.studyglows.navigation

sealed class Screen(val route: String) {
    object Welcome: Screen(route = "welcome")
    object Login: Screen(route = "login_screen")
    object Otp: Screen(route = "otp_screen")
    object Home: Screen(route = "dashboard_screen")
    object Dashboard: Screen(route = "dashboard_screen")
    object Explore: Screen(route = "explore_screen")
    object Lecture: Screen(route = "lecture_screen")
    object AllCourses: Screen(route = "all_courses_screen")
    object CourseDetails: Screen(route = "course_details_screen")
    object Cart: Screen(route = "cart_screen")
}
