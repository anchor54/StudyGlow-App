package com.example.studyglows.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.studyglows.screens.auth.common.models.AppUIEvent

@Composable
fun BaseScreenLayout(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier.background(Color(0xFFE6F1F8))) {
        HomeAppBar(onNavIconClicked = openDrawer) {}
        AppContentCard { content() }
    }
}