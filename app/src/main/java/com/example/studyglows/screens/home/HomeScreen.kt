package com.example.studyglows.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.studyglows.R
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.navigation.navgraphs.coursesNavGraph
import com.example.studyglows.screens.auth.common.models.HomeUIEvent

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    Log.d("HomeScreen", viewModel.toString())

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeUIEvent.NavigateCourseDetails -> {
                    val courseId = event.courseId
                    navHostController.navigate(
                        Screen.CourseDetails.route +
                                "?courseId=$courseId"
                    )
                }
                else -> {}
            }
        }
    }

    Column(modifier = modifier.background(Color(0xFFE6F1F8))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.study_glow_small_icon),
                contentDescription = "Study Glow Icon"
            )
            Text(
                text = "STUDY GLOWS",
                style = TextStyle(
                    fontSize = 22.sp,
                    color = Color(0xFF01304E),
                    fontWeight = FontWeight(900),
                    letterSpacing = 2.2.sp,
                )
            )
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.search),
                contentDescription = "Study Glow Icon"
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(
                topStart = 33.dp,
                topEnd = 33.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            val coursesNavController = rememberNavController()
            NavHost(
                navController = coursesNavController,
                startDestination = Route.COURSE_ROUTE.name,
                route = Route.HOME_ROUTE.name
            ) {
                coursesNavGraph(
                    navHostController = coursesNavController,
                    modifier = Modifier.padding(16.dp),
                    viewModel = viewModel
                )
            }
        }
    }
}