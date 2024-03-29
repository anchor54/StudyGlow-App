package com.example.studyglows.shared.components.drawermenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R

@Composable
fun NavDrawerContent(
    midMenuItems: List<MenuItemModel> = listOf(),
    selectedItem: String,
    onItemClicked: (String) -> Unit
) {
    val topMenuItems = listOf(
        MenuItemModel(
            id = "courses",
            activeIcon = R.drawable.book_active,
            inactiveIcon = R.drawable.book_inactive,
            title = "Courses",
            contentDescription = "Go to courses page",
        ),
        MenuItemModel(
            id = "readings",
            activeIcon = R.drawable.file_active,
            inactiveIcon = R.drawable.file_inactive,
            title = "Readings",
            contentDescription = "Go to readings page",
        ),
        MenuItemModel(
            id = "tests",
            activeIcon = R.drawable.icon_bulb_active,
            inactiveIcon = R.drawable.icon_bulb_inactive,
            title = "Tests",
            contentDescription = "Go to tests page",
        )
    )
    val bottomMenuItems = listOf(
        MenuItemModel(
            id = "profile",
            activeIcon = R.drawable.person_active,
            inactiveIcon = R.drawable.person_inactive,
            title = "My Profile",
            contentDescription = "Go to profile page",
        ),
        MenuItemModel(
            id = "cart",
            activeIcon = R.drawable.shopping_cart_active,
            inactiveIcon = R.drawable.shopping_cart_inactive,
            title = "My Cart",
            contentDescription = "Go to cart page",
        ),
        MenuItemModel(
            id = "settings",
            activeIcon = R.drawable.settings_active,
            inactiveIcon = R.drawable.settings_inactive,
            title = "Settings",
            contentDescription = "Go to settings page",
        )
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        MenuItems(
            menuItem = topMenuItems,
            activeItem = selectedItem,
            modifier = Modifier.fillMaxWidth()
        ) {
            onItemClicked(it)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFB1D4EA)),
        )
        MenuItems(
            menuItem = midMenuItems,
            activeItem = selectedItem,
            title = "Subcategories",
            modifier = Modifier.fillMaxWidth()
        ) {
            onItemClicked(it)
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFB1D4EA)),
        )
        MenuItems(
            menuItem = bottomMenuItems,
            activeItem = selectedItem,
            title = "My Account",
            modifier = Modifier.fillMaxWidth()
        ) {
            onItemClicked(it)
        }
    }
}

@Composable
fun NavDrawerHeader(
    userName: String = ""
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, top = 20.dp)
    ) {
        Text(
            text = "Welcome",
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF8798AD),
                letterSpacing = 0.1.sp,
            )
        )
        Text(
            text = userName,
            style = TextStyle(
                fontSize = 25.sp,
                lineHeight = 30.sp,
                color = Color(0xFF2E384D),
            )
        )
    }
}