package com.example.studyglows.screens.home.common.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabRowItem(
    val title: String,
    val icon: ImageVector? = null,
    val screen: @Composable () -> Unit,
)