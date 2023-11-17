package com.example.studyglows.navigation

import android.util.Base64
import com.example.studyglows.shared.model.NavItem
import com.google.gson.Gson

class TestSeriesPathCreator(private val navPath: List<NavItem> = listOf()) {
    fun addTestSeriesRoot(): TestSeriesPathCreator =
        TestSeriesPathCreator(
            navPath + NavItem(Route.TEST_SERIES_FLOW.name)
        )
    fun addTestDashboard(): TestSeriesPathCreator =
        TestSeriesPathCreator(
            navPath + NavItem(Screen.TestSeriesDashboard.route)
        )
    fun addExploreTest(): TestSeriesPathCreator =
        TestSeriesPathCreator(
            navPath + NavItem(Screen.ExploreTest.route)
        )
    fun addTestDetails(testId: String): TestSeriesPathCreator =
        TestSeriesPathCreator(
            navPath + NavItem(Screen.TestSeriesDetails.route, listOf(testId))
        )
    fun addTestAttempted(): TestSeriesPathCreator =
        TestSeriesPathCreator(
            navPath + NavItem(Screen.TestsAttempted.route)
        )
    fun addPopularTest(): TestSeriesPathCreator =
        TestSeriesPathCreator(
            navPath + NavItem(Screen.PopularTests.route)
        )
    fun addSavedTest(): TestSeriesPathCreator =
        TestSeriesPathCreator(
            navPath + NavItem(Screen.SavedTests.route)
        )
    fun addTestResult(testId: String): TestSeriesPathCreator =
        TestSeriesPathCreator(
            navPath + NavItem(Screen.TestResults.route, listOf(testId))
        )
    fun build(gson: Gson): String {
        val params = String(Base64.encode(gson.toJson(navPath).toByteArray(), Base64.DEFAULT), Charsets.UTF_8).replace(" ", "").replace("\n", "")
        return "${Screen.TestSeriesScreen.route}?params=$params"
    }
}