package com.example.studyglows.screens.testseries.model

import com.example.studyglows.R
import com.example.studyglows.shared.components.drawermenu.MenuItemModel

fun testNavDrawerContent() = listOf(
    MenuItemModel(
        id = "my_tests",
        activeIcon = R.drawable.light,
        inactiveIcon = R.drawable.light,
        title = "My Tests",
        contentDescription = "go to my tests screen"
    ),
    MenuItemModel(
        id = "explore_tests",
        activeIcon = R.drawable.explore,
        inactiveIcon = R.drawable.explore,
        title = "Explore Tests",
        contentDescription = "go to explore tests screen"
    ),
    MenuItemModel(
        id = "evaluation",
        activeIcon = R.drawable.scoreboard,
        inactiveIcon = R.drawable.scoreboard,
        title = "Evaluation",
        contentDescription = "go to attempted tests evaluation screen",
    ),
    MenuItemModel(
        id = "saved_tests",
        activeIcon = R.drawable.icon_star_inactive,
        inactiveIcon = R.drawable.icon_star_inactive,
        title = "Saved Tests",
        contentDescription = "go to my saved tests screen",
    )
)