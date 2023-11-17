package com.example.studyglows.screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.studyglows.network.apis.LoginApis
import com.example.studyglows.screens.auth.common.models.OTPRequest
import com.example.studyglows.screens.auth.common.models.AuthUIEvent
import com.example.studyglows.screens.auth.common.models.UIState
import com.example.studyglows.screens.auth.common.models.ValidationEvent
import com.example.studyglows.screens.auth.common.models.VerifyOTPRequest
import com.example.studyglows.utils.Constants.COUNTRY_CODE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginNetworkAPI: LoginApis,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    private val _validation = MutableStateFlow<ValidationEvent>(ValidationEvent.NoEvent())
    val validation = _validation.asStateFlow()

    fun onEvent(event: AuthUIEvent) {
        when (event) {
            is AuthUIEvent.PhoneNumberChanged -> {
                _uiState.value = _uiState.value.copy(
                    phoneNumber = event.phoneNumber,
                    isPhoneNumberValid = validatePhoneNumber(event.phoneNumber)
                )
            }
            is AuthUIEvent.OTPChanged -> {
                _uiState.value = _uiState.value.copy(
                    otp = event.otp,
                    isOTPValid = validateOTP(event.otp)
                )
            }
            is AuthUIEvent.OTPSend -> {
                getOTP(false)
            }
            is AuthUIEvent.OTPResend -> {
                getOTP(true)
            }
            is AuthUIEvent.OTPSubmit -> {
                verifyOTP()
            }
            else -> {}
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
            viewModelScope.launch(Dispatchers.IO) {
                _validation.emit(ValidationEvent.Loading(true))
                val res = loginNetworkAPI.verifyOTP(requestBody)
                _validation.emit(ValidationEvent.Loading(false))
                if (res.isSuccessful) {
                    res.body()?.access?.let { accessToken ->
                        _validation.emit(ValidationEvent.OTPVerifySuccess())
                    } ?: run {
                        _validation.emit(
                            ValidationEvent.OTPVerifyError(
                                res.body()?.message ?: "Something went wrong!"
                            )
                        )
                    }
                } else {
                    _validation.emit(ValidationEvent.OTPVerifyError("Something went wrong!"))
                }
            }

        }
    }

    private fun getOTP(isResent: Boolean) {
        if (uiState.value.isPhoneNumberValid) {
            val requestBody = OTPRequest(
                country_code = COUNTRY_CODE,
                phone = uiState.value.phoneNumber,
                resend = isResent
            )
            viewModelScope.launch(Dispatchers.IO) {
                val res =
                    if (isResent) loginNetworkAPI.resendOTP(requestBody)
                    else loginNetworkAPI.sendOTP(requestBody)
                _validation.emit(
                    if (res.isSuccessful) ValidationEvent.OTPSentSuccess()
                    else ValidationEvent.OTPSentError("Something went wrong!")
                )
            }
        }
    }
}