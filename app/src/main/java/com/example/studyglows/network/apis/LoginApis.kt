package com.example.studyglows.network.apis

import com.example.studyglows.screens.auth.common.constants.NetworkConstants.RESEND_OTP
import com.example.studyglows.screens.auth.common.constants.NetworkConstants.SEND_OTP
import com.example.studyglows.screens.auth.common.constants.NetworkConstants.VERIFY_OTP
import com.example.studyglows.screens.auth.common.models.OTPRequest
import com.example.studyglows.screens.auth.common.models.OTPResponse
import com.example.studyglows.screens.auth.common.models.VerifyOTPRequest
import com.example.studyglows.screens.auth.common.models.VerifyOTPResponse
import com.example.studyglows.utils.Constants.BASE_API_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApis {

    @POST(SEND_OTP)
    suspend fun sendOTP(@Body body: OTPRequest): Response<OTPResponse>

    @POST(RESEND_OTP)
    suspend fun resendOTP(@Body body: OTPRequest): Response<OTPResponse>

    @POST(VERIFY_OTP)
    suspend fun verifyOTP(@Body body: VerifyOTPRequest): Response<VerifyOTPResponse>
}