package com.example.studyglows.shared.components.drawermenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MenuItemModel(
    val id: String,
    val activeIcon: Int,
    val inactiveIcon: Int,
    val title: String,
    val contentDescription: String,
)

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    item: MenuItemModel,
    isActive: Boolean = false,
) {
    Box(
        modifier = modifier
            .background(Color(if (isActive) 0x66B1D4EA else 0x00000000))
    ) {
        Row(modifier = Modifier.padding(18.dp, 9.dp)) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    if (isActive) item.activeIcon else item.inactiveIcon,
                ),
                tint = Color(0xFF0369A9),
                contentDescription = item.contentDescription
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = item.title,
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.1.sp,
                )
            )
        }
    }
}