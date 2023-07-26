package com.example.studyglows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.studyglows.navigation.NavGraph
import com.example.studyglows.screens.auth.AuthViewModel
import com.example.studyglows.ui.theme.StudyGlowsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: AuthViewModel by viewModels()
        setContent {
            StudyGlowsTheme {
                val navController = rememberNavController()
                NavGraph(navHostController = navController)
            }
        }
    }
}