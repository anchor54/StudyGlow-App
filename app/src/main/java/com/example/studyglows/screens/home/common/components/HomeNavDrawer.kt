package com.example.studyglows.screens.home.common.components

import com.example.studyglows.R
import com.example.studyglows.shared.components.drawermenu.MenuItemModel

fun homeNavDrawerContent() = listOf(
        MenuItemModel(
            id = "my_courses",
            icon = R.drawable.icon_studyglow,
            title = "My Courses",
            contentDescription = "go to my courses screen"
        ),
        MenuItemModel(
            id = "explore_courses",
            icon = R.drawable.icon_find,
            title = "Explore Courses",
            contentDescription = "go to my explore courses screen"
        ),
        MenuItemModel(
            id = "live_classes",
            icon = R.drawable.icon_live_tv,
            title = "Live Classes",
            contentDescription = "go to my live classes screen",
        ),
        MenuItemModel(
            id = "saved_courses",
            icon = R.drawable.icon_star,
            title = "Saved",
            contentDescription = "go to my saved courses screen",
        )
    )