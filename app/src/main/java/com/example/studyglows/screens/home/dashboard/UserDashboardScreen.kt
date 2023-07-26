package com.example.studyglows.screens.home.dashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.common.components.ContinueWatchingCard
import com.example.studyglows.screens.home.common.components.CourseCard
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.screens.home.common.models.OngoingCourse
import com.example.studyglows.utils.Utils

@Preview(showBackground = true)
@Composable
fun UserDashboardScreen(
//    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<HomeViewModel>()
    val popularCourses by viewModel.popularCourses.collectAsState()
    val currentCourses by viewModel.currentlyWatching.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getCurrentlyWatchingCourses()
        viewModel.getPopularCourses()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ContinueWatchingCourses(courses = currentCourses, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(40.dp))
        PopularCourses(courses = popularCourses, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun PopularCourses(
    modifier: Modifier = Modifier,
    courses: List<Course>
) {
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
            itemsIndexed(courses) { _, course ->
                Box(modifier = Modifier.width(165.dp).height(300.dp)) {
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
    }
}

@Composable
fun ContinueWatchingCourses(
    modifier: Modifier = Modifier,
    courses: List<OngoingCourse>
) {
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
        if (courses.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Nothing here?",
                    style = TextStyle(
                        fontSize = 21.sp,
                        lineHeight = 25.2.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.15.sp,
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Add Your favourite courses",
                    style = TextStyle(
                        fontSize = 25.sp,
                        lineHeight = 30.sp,
                        color = Color(0xFF2E384D),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier.width(215.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier.padding(24.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF025284)
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Explore courses",
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                            letterSpacing = 1.25.sp,
                        )
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.height(5.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                itemsIndexed(courses) { _, course ->
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