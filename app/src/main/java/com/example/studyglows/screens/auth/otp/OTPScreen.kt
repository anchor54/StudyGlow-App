package com.example.studyglows.screens.auth.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.screens.auth.common.components.LoginButton
import com.example.studyglows.screens.auth.common.components.LoginField
import com.example.studyglows.screens.auth.common.models.UIEvent
import com.example.studyglows.screens.auth.common.models.ValidationEvent
import com.example.studyglows.screens.viewmodels.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPScreen(
    navHostController: NavHostController,
    viewModel: LoginViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    suspend fun showSnackbar(message: String) {
        snackbarHostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short,
        )
    }

    LaunchedEffect(key1 = context) {
        viewModel.validation.collect { event ->
            when (event) {
                is ValidationEvent.OTPSentError -> {
                    showSnackbar(event.message)
                }
                is ValidationEvent.OTPVerifySuccess -> {
                    // Navigate to dashboard
                }
                is ValidationEvent.OTPVerifyError -> {
                    showSnackbar(event.message)
                }
                else -> {
                    // Do Nothing
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        val loginState = viewModel.uiState.collectAsState().value

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFE6F1F8)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 21.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "OTP page logo",
                    contentScale = ContentScale.None,
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 30.dp)
                )
                LoginField(
                    text = loginState.phoneNumber,
                    pretext = "+91",
                    label = "YOUR PHONE",
                    keyboardType = KeyboardType.Number,
                    onTextChanged = { viewModel.onEvent(UIEvent.PhoneNumberChanged(it)) },
                    modifier = Modifier.fillMaxWidth(1f)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 30.dp)
                )
                LoginField(
                    text = loginState.otp,
                    label = "ENTER OTP",
                    keyboardType = KeyboardType.Password,
                    onTextChanged = { viewModel.onEvent(UIEvent.OTPChanged(it)) },
                    modifier = Modifier.fillMaxWidth(1f)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 21.dp)
                )
                LoginButton(
                    buttonText = "CONFIRM",
                    backgroundColor = Color(0xFFE6F1F8),
                    onClick = {
                        viewModel.onEvent(UIEvent.OTPSubmit())
                    },
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 14.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "RESEND OTP",
                        modifier = Modifier.clickable(true) {
                            viewModel.onEvent(UIEvent.OTPResend())
                        },
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 14.4.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF023F66),
                            textAlign = TextAlign.Right,
                            letterSpacing = 1.25.sp,
                            textDecoration = TextDecoration.Underline,
                        )
                    )
                }
            }
        }
    }
}
