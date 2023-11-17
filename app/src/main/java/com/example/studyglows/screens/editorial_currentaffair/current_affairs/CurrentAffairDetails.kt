package com.example.studyglows.screens.editorial_currentaffair.current_affairs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.studyglows.R
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.component.CurrentAffairDetailContent
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairItem
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.example.studyglows.utils.Utils.findIndex

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentAffairDetails(
    modifier: Modifier = Modifier,
    currentAffairs: List<CurrentAffairItem>,
    viewModel: CurrentAffairsViewModel,
    sharedViewModel: SharedViewModel,
    itemId: String,
    onClose: () -> Unit
) {
    val details by viewModel.currentAffairDetails.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val pagerCount by remember(currentAffairs) {
        derivedStateOf { currentAffairs.size }
    }
    val currentPage by remember(itemId) {
        derivedStateOf {
            currentAffairs.findIndex { it.id == itemId }
        }
    }
    val pagerState = rememberPagerState(currentPage)
    LaunchedEffect(key1 = currentPage) {
        if (currentAffairs.size > currentPage) {
            viewModel.getCurrentAffairDetails(currentAffairs[currentPage].id)
        }
    }
    LaunchedEffect(key1 = pagerState.currentPage) {
        if (currentAffairs.size > pagerState.currentPage) {
            viewModel.getCurrentAffairDetails(currentAffairs[pagerState.currentPage].id)
        }
    }
    LaunchedEffect(key1 = loading) {
        sharedViewModel.isLoading(loading)
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.error.collect {
            sharedViewModel.showError(it)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.close),
                    contentDescription = "close details",
                    tint = Color.White
                )
            }
        }
        HorizontalPager(
            pageCount = pagerCount,
            state = pagerState,
            pageSpacing = 20.dp
        ) {
            CurrentAffairDetailContent(details = details)
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