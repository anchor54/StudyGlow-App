package com.example.studyglows.navigation

import android.util.Base64
import com.example.studyglows.shared.model.NavItem
import com.google.gson.Gson

object DeepLink {
    val BASE_URI = "http://live-studyglow-frontend.s3-website.ap-south-1.amazonaws.com"
    val EditorialDetail = "$BASE_URI/editorial/{editorialId}"
    fun buildEditorialDetailDeeplink(id: String) = "$BASE_URI/editorial/$id"
    val TestSeriesScreen = "$BASE_URI/testseries/{screen_params}"
    fun buildTestDetailDeeplink(id: String): String {
        val encodedPath = Base64.encode(
            "details/$id".toByteArray(),
            Base64.DEFAULT
        )
        return "$BASE_URI/testseries/$encodedPath"
    }
    val TestResult = "$BASE_URI/testresult/{testId}"
    fun buildTestResultDeeplink(id: String) = "$BASE_URI/testresult/$id"
}