package com.example.studyglows.screens.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.home.HomeViewModel

@Composable
fun SavedCoursesScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: HomeViewModel
) {
    val savedCourses by viewModel.savedCourses.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllSavedCourses()
    }

    Column(modifier = modifier) {
        Text(
            text = "Saved Courses",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.height(18.dp))
        LazyColumn {
            items(savedCourses.size) {
                val savedItem = savedCourses[it]
                SavedItem(
                    savedItem = savedItem,
                    showDetailsClicked = {
                        viewModel.triggerEvent(HomeUIEvent.NavigateCourseDetails(savedItem.id))
                    },
                    addToCartClicked = {
                        viewModel.addSavedCourseToCart(savedItem.id)
                    }
                )
            }
        }
    }
}