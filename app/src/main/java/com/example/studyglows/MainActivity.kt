package com.example.studyglows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.studyglows.navigation.NavGraph
import com.example.studyglows.screens.auth.otp.OTPScreen
import com.example.studyglows.screens.viewmodels.LoginViewModel
import com.example.studyglows.ui.theme.StudyGlowsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: LoginViewModel by viewModels()
        setContent {
            StudyGlowsTheme {
                val navController = rememberNavController()
                NavGraph(navHostController = navController)
            }
        }
    }
}