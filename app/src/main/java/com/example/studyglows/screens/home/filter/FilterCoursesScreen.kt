package com.example.studyglows.screens.home.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.common.components.CourseCard
import com.example.studyglows.shared.components.VerticalGrid

@Composable
fun FilterCoursesScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val filteredCourses by viewModel.filteredCourses.collectAsState()
    val filterTitle = navHostController.currentBackStackEntry?.arguments?.getString("title") ?: ""

    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Showing results for:",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF8798AD),
                    letterSpacing = 0.1.sp,
                )
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.filter_icon),
                contentDescription = "Filter",
                modifier = Modifier.clickable { viewModel.triggerEvent(HomeUIEvent.ShowFilters()) }
            )
        }
        Text(
            text = filterTitle,
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.height(18.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(filteredCourses) {
                CourseCard(courseDetails = it) { courseId ->
                    viewModel.triggerEvent(HomeUIEvent.NavigateCourseDetails(courseId))
                }
            }
        }
    }
}