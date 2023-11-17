package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.home.HomeViewModel

@Composable
fun ContinueWatchingCourses(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val currentCourses by viewModel.currentlyWatching.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getCurrentlyWatchingCourses()
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Continue Watching",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        if (currentCourses.isEmpty()) {
            EmptyContinueWatching {
                viewModel.triggerEvent(HomeUIEvent.NavigateExploreCourses())
            }
        } else {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                itemsIndexed(currentCourses) { _, course ->
                    ContinueWatchingCard(
                        imageUrl = course.imageUrl ?: "",
                        courseName = course.courseName ?: "",
                        lastChapterName = course.currentChapter ?: "",
                        progress = course.progress ?: 0f
                    )
                }
            }
        }
    }
}