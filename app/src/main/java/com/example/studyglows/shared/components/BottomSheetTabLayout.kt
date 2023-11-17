package com.example.studyglows.shared.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.home.common.models.TabRowItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheetTabLayout(
    tabRowItems: List<TabRowItem> = listOf(),
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        if (tabRowItems.size > 1) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    if (tabPositions.size > pagerState.currentPage) {
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                                .background(Color(0x1F025284))
                                .fillMaxSize()
                                .clip(RoundedCornerShape(100.dp)),
                        )
                    }
                },
            ) {
                tabRowItems.forEachIndexed { index, item ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        icon = {
                            item.icon?.let { icon ->
                                Icon(imageVector = icon, contentDescription = "")
                            }
                        },
                        text = {
                            Text(
                                text = item.title,
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    lineHeight = 18.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF025284),
                                    letterSpacing = 1.25.sp,
                                )
                            )
                        }
                    )
                }
            }
        }
        HorizontalPager(
            pageCount = tabRowItems.size,
            state = pagerState,
        ) {
            tabRowItems[pagerState.currentPage].screen()
        }
    }
}