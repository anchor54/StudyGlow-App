package com.example.studyglows.navigation

import android.util.Base64
import com.example.studyglows.shared.model.NavItem
import com.google.gson.Gson

class TestPathCreator(private val navPath: List<NavItem> = listOf()) {
    fun addTestRoot(): TestPathCreator =
        TestPathCreator(
            navPath + NavItem(Route.TEST_FLOW.name)
        )
    fun addTestWelcome(testId: String): TestPathCreator =
        TestPathCreator(
            navPath + NavItem(Screen.TestWelcome.route, listOf(testId))
        )
    fun addTestQuestions(testId: String): TestPathCreator =
        TestPathCreator(
            navPath + NavItem(Screen.TestQuestions.route, listOf(testId))
        )
    fun build(gson: Gson): String {
        val params = String(Base64.encode(gson.toJson(navPath).toByteArray(), Base64.DEFAULT), Charsets.UTF_8).replace(" ", "").replace("\n", "")
        return "${Screen.TestScreen.route}?params=$params"
    }
}