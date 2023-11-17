package com.example.studyglows.shared.components.drawermenu

import androidx.navigation.NavHostController
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen

interface IDrawerNavigation {
    fun handleDrawerNavigation(itemId: String)
}

enum class DrawerItemId(val id: String) {
    COURSES("courses"),
    READINGS("readings"),
    TESTS("tests"),
    PROFILE("profile"),
    CART("cart"),
    SETTINGS("settings")
}

open class BaseDrawerNavigation(
    private val navHostController: NavHostController
): IDrawerNavigation {
    override fun handleDrawerNavigation(itemId: String) {
        when(itemId) {
            DrawerItemId.COURSES.id -> navHostController.navigate(Route.HOME_ROUTE.name)
            DrawerItemId.READINGS.id -> navHostController.navigate(Route.EDITORIAL_ROUTE.name)
            DrawerItemId.TESTS.id -> navHostController.navigate(Screen.TestSeriesScreen.route)
            DrawerItemId.PROFILE.id -> navHostController.navigate(Screen.Profile.route)
            DrawerItemId.CART.id -> navHostController.navigate(Route.CART_ROUTE.name)
            DrawerItemId.SETTINGS.id -> navHostController.navigate(Route.SETTINGS_ROUTE.name)
            else -> {}
        }
    }
}