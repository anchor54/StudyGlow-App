package com.example.studyglows.screens.home.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studyglows.R
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.common.components.CourseCard
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.shared.components.VerticalGrid
import com.example.studyglows.utils.Utils

@Preview(showBackground = true)
@Composable
fun ExploreCoursesScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<HomeViewModel>()
    val mostPopularSubjects by viewModel.popularCoursesBySubjects.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getTopCoursesOfPopularSubjects()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.filter_icon),
                contentDescription = "Filter"
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        LazyColumn {
            itemsIndexed(mostPopularSubjects.popularSubjectList) { i, subject ->
                TopCourses(
                    subjectName = subject.subjectName ?: "",
                    courses = subject.courses ?: listOf()
                )
                if (i < mostPopularSubjects.popularSubjectList.size - 1) {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}

@Composable
fun TopCourses(
    subjectName: String,
    courses: List<Course>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = subjectName,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 20.4.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.15.sp,
                )
            )
            Text(
                text = "VIEW ALL",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF025284),
                    letterSpacing = 1.25.sp,
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        VerticalGrid(
            columns = 2,
            items = courses,
            rowSpacing = 16.dp,
            columnSpacing = 13.dp
        ) { course ->
            CourseCard(
                imageUrl = course.imageUrl ?: "",
                courseName = course.title ?: "",
                originalPrice = Utils.amountWithRupeeSymbol(course.originalPrice ?: 0f),
                discountedPrice = Utils.amountWithRupeeSymbol(course.discountedPrice ?: 0f),
                purchased = course.isBought ?: false,
                tagText = course.tag ?: "",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}