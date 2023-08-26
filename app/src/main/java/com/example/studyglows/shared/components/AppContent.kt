package com.example.studyglows.shared.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.navgraphs.coursesNavGraph
import com.example.studyglows.screens.home.HomeViewModel

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navHostController: NavHostController
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = 33.dp,
            topEnd = 33.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        NavHost(
            navController = navHostController,
            startDestination = Route.COURSE_ROUTE.name,
            route = Route.DASHBOARD_ROUTE.name
        ) {
            coursesNavGraph(
                navHostController = navHostController,
                modifier = Modifier.padding(16.dp),
                viewModel = viewModel
            )
        }
    }
}