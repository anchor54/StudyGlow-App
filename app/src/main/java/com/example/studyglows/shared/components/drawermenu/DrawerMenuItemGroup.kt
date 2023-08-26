package com.example.studyglows.shared.components.drawermenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuItems(
    modifier: Modifier = Modifier,
    title: String = "",
    menuItem: List<MenuItemModel>,
    activeItem: String = "",
    onMenuItemSelected: (String) -> Unit
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(14.dp))
        if (title.isNotEmpty()) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 15.6.sp,
                    color = Color(0xFF8798AD),
                    letterSpacing = 0.4.sp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 19.dp, bottom = 20.dp)
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            menuItem.map { item ->
                MenuItem(
                    item = item,
                    isActive = item.id == activeItem,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onMenuItemSelected(item.id)
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(26.dp))
    }
}