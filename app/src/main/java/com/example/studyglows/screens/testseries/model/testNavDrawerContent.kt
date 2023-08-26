package com.example.studyglows.screens.testseries.model

import com.example.studyglows.R
import com.example.studyglows.shared.components.drawermenu.MenuItemModel

fun testNavDrawerContent() = listOf(
    MenuItemModel(
        id = "my_tests",
        icon = R.drawable.light,
        title = "My Tests",
        contentDescription = "go to my tests screen"
    ),
    MenuItemModel(
        id = "explore_tests",
        icon = R.drawable.explore,
        title = "Explore Tests",
        contentDescription = "go to explore tests screen"
    ),
    MenuItemModel(
        id = "evaluation",
        icon = R.drawable.scoreboard,
        title = "Evaluation",
        contentDescription = "go to attempted tests evaluation screen",
    ),
    MenuItemModel(
        id = "saved_tests",
        icon = R.drawable.icon_star,
        title = "Saved Tests",
        contentDescription = "go to my saved tests screen",
    )
)