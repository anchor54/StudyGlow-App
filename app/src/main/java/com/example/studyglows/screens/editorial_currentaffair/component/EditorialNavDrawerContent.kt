package com.example.studyglows.screens.editorial_currentaffair.component

import com.example.studyglows.R
import com.example.studyglows.shared.components.drawermenu.MenuItemModel

fun editorialNavDrawerContent() = listOf(
    MenuItemModel(
        id = "editorials",
        icon = R.drawable.article,
        title = "Editorials",
        contentDescription = "go to editorials screen"
    ),
    MenuItemModel(
        id = "current_affairs",
        icon = R.drawable.power_bolt,
        title = "Current Affairs",
        contentDescription = "go to my current affairs screen"
    ),
    MenuItemModel(
        id = "latest_vacancies",
        icon = R.drawable.vacant_chair,
        title = "Latest Vacancies",
        contentDescription = "go to my latest vacancies screen",
    ),
    MenuItemModel(
        id = "saved_editorials",
        icon = R.drawable.icon_star,
        title = "Saved",
        contentDescription = "go to my saved editorials screen",
    )
)