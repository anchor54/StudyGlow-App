package com.example.studyglows.shared.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.shared.model.SearchResultItem

@Composable
fun BaseScreenLayout(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    searchResult: List<SearchResultItem>,
    onResultItemClicked: (String) -> Unit,
    onSearch: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    content: @Composable () -> Unit
) {
    var showSearchScreen by remember { mutableStateOf(false) }
    if (showSearchScreen) {
        Log.d("BaseScreenLayout: ", "Showing search screen")
        SearchScreen(
            results = searchResult,
            onResultItemClicked = {
                onResultItemClicked(it)
                showSearchScreen = false
            },
            onSearch = onSearch,
            onSearchClicked = {
                showSearchScreen = false
                onSearchClicked(it)
           },
            onBackClicked = { showSearchScreen = false }
        )
    } else {
        Column(
            modifier = modifier
                .background(Color(0xFFE6F1F8))
                .verticalScroll(rememberScrollState())
        ) {
            HomeAppBar(
                onNavIconClicked = openDrawer,
                onSearchClicked = {
                    showSearchScreen = true
                }
            )
            AppContentCard(modifier = Modifier.weight(1f)) { content() }
        }
    }
}