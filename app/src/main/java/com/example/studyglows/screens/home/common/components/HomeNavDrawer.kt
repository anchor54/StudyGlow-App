package com.example.studyglows.screens.home.common.components

import com.example.studyglows.R
import com.example.studyglows.shared.components.drawermenu.MenuItemModel

fun homeNavDrawerContent() = listOf(
        MenuItemModel(
            id = "my_courses",
            activeIcon = R.drawable.icon_studyglow_active,
            inactiveIcon = R.drawable.icon_studyglow_inactive,
            title = "My Courses",
            contentDescription = "go to my courses screen"
        ),
        MenuItemModel(
            id = "explore_courses",
            activeIcon = R.drawable.icon_find_active,
            inactiveIcon = R.drawable.icon_find_inactive,
            title = "Explore Courses",
            contentDescription = "go to my explore courses screen"
        ),
        MenuItemModel(
            id = "live_classes",
            activeIcon = R.drawable.live_tv_active,
            inactiveIcon = R.drawable.icon_live_tv_inactive,
            title = "Live Classes",
            contentDescription = "go to my live classes screen",
        ),
        MenuItemModel(
            id = "saved_courses",
            activeIcon = R.drawable.icon_star_active,
            inactiveIcon = R.drawable.icon_star_inactive,
            title = "Saved",
            contentDescription = "go to my saved courses screen",
        )
    )