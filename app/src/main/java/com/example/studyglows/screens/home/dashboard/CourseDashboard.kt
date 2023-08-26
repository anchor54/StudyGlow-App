package com.example.studyglows.screens.home.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.common.components.ContinueWatchingCourses
import com.example.studyglows.screens.home.common.components.PopularCourses

@Composable
fun CoursesDashboard(
    navHostController: NavHostController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
   LaunchedEffect(key1 = Unit) {
       viewModel.uiEvent.collect { event ->
           when(event) {
               is HomeUIEvent.NavigateExploreCourses -> {
                   navHostController.navigate(Screen.Explore.route)
               }
               else -> {}
           }
       }
   }

    Column(modifier = modifier) {
        ContinueWatchingCourses(
            viewModel = viewModel,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        PopularCourses(
            viewModel = viewModel,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}