package com.example.studyglows.screens.login

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.studyglows.R
import com.example.studyglows.screens.login.components.LoginButton
import com.example.studyglows.screens.login.components.LoginField
import com.example.studyglows.screens.viewmodels.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studyglows.screens.login.models.UIEvent

@Composable
fun LoginScreen() {
    Box(modifier = Modifier
        .fillMaxSize(1f)
        .background(color = Color(0xFFE6F1F8))
    ) {
        val viewModel: LoginViewModel = viewModel()

        Column(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .align(Alignment.TopCenter),
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
                text = "",
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
                text = "",
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
                onClick = { viewModel.onEvent(UIEvent.Submit()) },
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

@Preview(showBackground = false)
@Composable
fun PreviewRoundedEditText() {
    LoginScreen()
}
