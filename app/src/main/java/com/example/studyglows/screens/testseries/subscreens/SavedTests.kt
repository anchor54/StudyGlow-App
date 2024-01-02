package com.example.studyglows.screens.testseries.subscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
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
import com.example.studyglows.screens.testseries.TestSeriesViewModel
import com.example.studyglows.screens.testseries.components.SavedTestItem
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun SavedTests(
    modifier: Modifier = Modifier,
    viewModel: TestSeriesViewModel,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    val savedCourses by viewModel.savedCourses.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllSavedCourses()
    }

    Column(modifier = modifier) {
        Text(
            text = "Saved Test Series",
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            ),
            modifier = Modifier.padding(16.dp, 20.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Divider(color = Color(0xFFB1D4EA), thickness = 1.dp, modifier = Modifier.padding(horizontal = 10.dp))
        LazyColumn {
            items(savedCourses.size) {
                val savedItem = savedCourses[it]
                SavedTestItem(
                    savedTest = savedItem,
                    showDetailsClicked = {
                        navHostController.navigate("${Screen.TestSeriesDetails.route}/${savedItem.id}")
                    },
                    addToCartClicked = {
                        viewModel.addSavedCourseToCart(savedItem.id)
                    }
                )
            }
        }
    }
}