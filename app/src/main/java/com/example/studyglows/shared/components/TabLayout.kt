package com.example.studyglows.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.profile.TabEntity
import com.example.studyglows.utils.UIUtils.innerShadow
import com.example.studyglows.utils.UIUtils.outerShadow


@Composable
fun TabLayout(
    modifier: Modifier = Modifier,
    tabNames: List<String> = listOf(),
    tabContent: @Composable (Int) -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            tabNames.mapIndexed { i, tabName ->
                TabItem(
                    selected = selectedTab == i,
                    tabTitle =  tabName,
                    modifier = Modifier.weight(1f),
                    onTabSelected = { selectedTab = i }
                )
            }
        }
        tabContent(selectedTab)
    }
}


@Composable
fun TabItem(
    selected: Boolean = false,
    tabTitle: String = "",
    modifier: Modifier = Modifier,
    onTabSelected: () -> Unit
) {
    Box(
        modifier = modifier
            .outerShadow(
                color = if (selected) Color.Transparent else Color(0x80CDD6DD),
                offsetY = 8.dp,
                blurRadius = 8.dp,
                spread = 7.dp,
            )
            .innerShadow(
                blur = 8.dp,
                color = if (selected) Color(0x80CDD6DD) else Color.Transparent,
                offsetY = -(8).dp,
                offsetX = 8.dp
            )
            .background(if (selected) Color(0xFFE6F1F8) else Color.White)
            .clickable { onTabSelected() }
    ) {
        Text(
            text = tabTitle,
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                color = Color(0xFF2E384D),
                textAlign = TextAlign.Center,
                letterSpacing = 0.4.sp,
            ),
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(0.dp, 12.dp)
        )
    }
}