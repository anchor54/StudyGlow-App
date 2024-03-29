package com.example.studyglows.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.UserApis
import com.example.studyglows.screens.auth.common.models.User
import com.example.studyglows.screens.home.common.models.PurchasedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userApis: UserApis
): ViewModel() {
    private val _userData = MutableStateFlow<User>(User(id = 0))
    val userData = _userData.asStateFlow()

    private val _purchasedCourses = MutableStateFlow<List<PurchasedItem>>(listOf())
    val purchasedCourses = _purchasedCourses.asStateFlow()

    private val _purchasedTestSeries = MutableStateFlow<List<PurchasedItem>>(listOf())
    val purchasedTestSeries = _purchasedTestSeries.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    fun getUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = userApis.getUserDetails("")
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _userData.value = it
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun getAllPurchasedCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = userApis.getPurchasedCourses("")
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let { courseList ->
                    _purchasedCourses.value = courseList.map {
                        PurchasedItem(
                            imageUrl = it.imageUrl,
                            title = it.title,
                            subtitle = "Purchased on ${SimpleDateFormat("dd/MM/yyyy").format(it.purchaseDate)}"
                        )
                    }
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }

    fun getAllPurchasedTests() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = userApis.getPurchasedTestSeries("")
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let { courseList ->
                    _purchasedTestSeries.value = courseList.map {
                        PurchasedItem(
                            imageUrl = it.imageUrl,
                            title = it.title,
                            subtitle = "Purchased on ${SimpleDateFormat("dd/MM/yyyy").format(it.purchaseDate)}"
                        )
                    }
                }
            } else {
                _error.value = response.message() ?: "Something went wrong"
            }
        }
    }
}