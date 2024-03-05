package com.example.studyglows.screens.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.UserApis
import com.example.studyglows.screens.auth.common.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val userApis: UserApis
): ViewModel() {
    private val _userData = MutableStateFlow<User>(User(id = 0))
    val userData = _userData.asStateFlow()

    fun getUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userApis.getUserDetails("")
            if (response.isSuccessful) {
                response.body()?.let {
                    _userData.value = it
                }
            }
        }
    }
}