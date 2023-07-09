package com.example.studyglows.screens.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.studyglows.network.LoginApis
import com.example.studyglows.screens.login.models.OTPRequest
import com.example.studyglows.screens.login.models.UIEvent
import com.example.studyglows.screens.login.models.UIState
import com.example.studyglows.screens.login.models.VerifyOTPRequest
import com.example.studyglows.utils.Constants.BASE_API_URL
import com.example.studyglows.utils.Constants.COUNTRY_CODE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel: ViewModel() {
    private val loginNetworkAPI: LoginApis by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApis::class.java)
    }

    private val _uiState = mutableStateOf(UIState())
    val uiState: State<UIState> = _uiState

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.PhoneNumberChanged -> {
                _uiState.value = _uiState.value.copy(
                    phoneNumber = event.phoneNumber,
                    isPhoneNumberValid = validatePhoneNumber(event.phoneNumber)
                )
            }
            is UIEvent.OTPChanged -> {
                _uiState.value = _uiState.value.copy(
                    phoneNumber = event.otp,
                    isOTPValid = validateOTP(event.otp)
                )
            }
            is UIEvent.OTPSend -> {
                getOTP(false)
            }
            is UIEvent.OTPResend -> {
                getOTP(true)
            }
            is UIEvent.Submit -> {
                verifyOTP()
            }
        }
    }

    private fun validatePhoneNumber(input: String): Boolean {
        val regexPattern = """^\d{10}$""".toRegex()
        return regexPattern.matches(input)
    }

    private fun validateOTP(input: String): Boolean {
        val regexPattern = """^\d{6}$""".toRegex()
        return regexPattern.matches(input)
    }

    private fun verifyOTP() {
        if (uiState.value.isPhoneNumberValid && uiState.value.isOTPValid) {
            val requestBody = VerifyOTPRequest(
                country_code = COUNTRY_CODE,
                phone = uiState.value.phoneNumber,
                otp = uiState.value.otp
            )
            loginNetworkAPI.verifyOTP(requestBody)
        }
    }

    private fun getOTP(isResent: Boolean) {
        if (uiState.value.isPhoneNumberValid) {
            val requestBody = OTPRequest(
                country_code = COUNTRY_CODE,
                phone = uiState.value.phoneNumber,
                resend = isResent
            )
            if (isResent) {
                loginNetworkAPI.resendOTP(requestBody)
            } else {
                loginNetworkAPI.sendOTP(requestBody)
            }
        }
    }
}