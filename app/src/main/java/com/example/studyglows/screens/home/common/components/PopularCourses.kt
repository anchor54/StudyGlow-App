package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.studyglows.MainActivity
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.utils.Utils

@Composable
fun PopularCourses(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
) {
    val popularCourses by viewModel.popularCourses.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getPopularCourses()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Popular Courses",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            state = rememberLazyListState(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(popularCourses) { _, course ->
                CourseCard(
                    courseDetails = course,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    viewModel.triggerEvent(HomeUIEvent.NavigateCourseDetails(course.courseId))
                }
            }
        }
    }
}