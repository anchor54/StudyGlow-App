package com.example.studyglows.preferences

import android.content.Context
import com.example.studyglows.utils.Constants.TOKEN_PREFERENCE
import com.example.studyglows.utils.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs = context.getSharedPreferences(TOKEN_PREFERENCE, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().apply {
            putString(USER_TOKEN, token)
            apply()
        }
    }

    fun getToken() = prefs.getString(USER_TOKEN, null)
}