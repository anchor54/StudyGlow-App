package com.example.studyglows.screens.home.allcourses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.common.components.CourseCard
import com.example.studyglows.shared.components.VerticalGrid
import com.example.studyglows.utils.Utils

@Composable
fun AllCoursesScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val courses by viewModel.subjectSpecificCourses.collectAsState()
    val subject = navHostController.currentBackStackEntry?.arguments?.getString("subject")

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCoursesForSubject(subject ?: "UPSC")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = courses.subjectName ?: "",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        VerticalGrid(
            columns = 2,
            items = courses.courses ?: listOf(),
            rowSpacing = 16.dp,
            columnSpacing = 16.dp
        ) { course ->
            CourseCard(course) {
                viewModel.triggerEvent(HomeUIEvent.NavigateCourseDetails(it))
            }
        }
    }
}