package com.example.studyglows.screens.editorial_currentaffair.component

import com.example.studyglows.R
import com.example.studyglows.shared.components.drawermenu.MenuItemModel

fun editorialNavDrawerContent() = listOf(
    MenuItemModel(
        id = "editorials",
        activeIcon = R.drawable.article_active,
        inactiveIcon = R.drawable.article_active,
        title = "Editorials",
        contentDescription = "go to editorials screen"
    ),
    MenuItemModel(
        id = "current_affairs",
        activeIcon = R.drawable.power_bolt,
        inactiveIcon = R.drawable.power_bolt,
        title = "Current Affairs",
        contentDescription = "go to my current affairs screen"
    ),
    MenuItemModel(
        id = "latest_vacancies",
        activeIcon = R.drawable.vacant_chair,
        inactiveIcon = R.drawable.vacant_chair,
        title = "Latest Vacancies",
        contentDescription = "go to my latest vacancies screen",
    ),
    MenuItemModel(
        id = "saved_editorials",
        activeIcon = R.drawable.icon_star_inactive,
        inactiveIcon = R.drawable.icon_star_inactive,
        title = "Saved",
        contentDescription = "go to my saved editorials screen",
    )
)