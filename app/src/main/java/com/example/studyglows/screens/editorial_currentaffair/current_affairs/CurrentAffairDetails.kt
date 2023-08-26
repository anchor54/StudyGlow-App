package com.example.studyglows.screens.editorial_currentaffair.current_affairs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.navigation.Route
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.component.CurrentAffairDetailContent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentAffairDetails(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: CurrentAffairsViewModel
) {
    val currentAffairs by viewModel.currentAffairs.collectAsState()
    val pagerCount by remember(currentAffairs) {
        derivedStateOf { currentAffairs.size }
    }
    val pagerState = rememberPagerState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCurrentAffairs()
    }

    Box(modifier = modifier) {
        Box(modifier = Modifier
            .fillMaxSize()
            .blur(radius = 25.dp))
        Column(modifier = Modifier.fillMaxWidth(0.8f), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.close),
                    contentDescription = "close details",
                    modifier = Modifier.clickable { navHostController.navigate(Route.CURRENT_AFFAIRS_ROUTE.name) }
                )
            }
            HorizontalPager(
                pageCount = pagerCount,
                state = pagerState,
                modifier = Modifier.heightIn(0.dp, 480.dp)
            ) {
                CurrentAffairDetailContent(viewModel = viewModel, id = currentAffairs[it].id)
            }
            Text(
                text = "Swipe to read next >>>",
                style = TextStyle(
                    fontSize = 21.sp,
                    lineHeight = 25.2.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF025284),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.15.sp,
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}