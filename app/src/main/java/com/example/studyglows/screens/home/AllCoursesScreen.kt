package com.example.studyglows.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.studyglows.screens.home.common.components.CourseCard
import com.example.studyglows.shared.components.VerticalGrid
import com.example.studyglows.utils.Utils

@Preview(showBackground = false)
@Composable
fun AllCoursesScreen() {
    val viewModel = viewModel<HomeViewModel>()
    val courses by viewModel.subjectSpecificCourses.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCoursesForSubject("UPSC")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
        ) {
            CourseCard(
                imageUrl = it.imageUrl ?: "",
                courseName = it.title ?: "",
                originalPrice = Utils.amountWithRupeeSymbol(it.originalPrice ?: 0f),
                discountedPrice = Utils.amountWithRupeeSymbol(it.discountedPrice ?: 0f),
                purchased = it.isBought ?: false,
                tagText = it.tag ?: ""
            )
        }
    }
}