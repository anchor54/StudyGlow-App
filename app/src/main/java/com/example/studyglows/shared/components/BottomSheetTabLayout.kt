package com.example.studyglows.shared.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.home.common.models.TabRowItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheetTabLayout(
    modifier: Modifier = Modifier,
    tabRowItems: List<TabRowItem> = listOf(),
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxHeight(0.5f).verticalScroll(rememberScrollState())) {
        if (tabRowItems.size > 1) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = {},
            ) {
                tabRowItems.forEachIndexed { index, item ->
                    BottomSheetTab(
                        selected = pagerState.currentPage == index,
                        selectedContentColor = Color(0x1F025284),
                        unselectedContentColor = Color(0x00000000),
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

@Composable
fun BottomSheetTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor,
) {
    Box(modifier = modifier
        .background(
            color = if (selected) selectedContentColor else unselectedContentColor,
            shape = RoundedCornerShape(50.dp)
        )
        .selectable(
            selected = selected,
            enabled = enabled,
            onClick = onClick
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                ,
            horizontalArrangement = Arrangement.Center
        ) {
            if (text != null) {
                Box(Modifier.layoutId("text")) { text() }
            }
            Box(modifier = Modifier.width(5.dp))
            if (icon != null) {
                Box(Modifier.layoutId("icon")) { icon() }
            }
        }
    }
}