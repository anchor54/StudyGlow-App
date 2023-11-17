package com.example.studyglows.screens.editorial_currentaffair.vacancies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.example.studyglows.screens.editorial_currentaffair.vacancies.model.NotificationItem
import com.example.studyglows.screens.editorial_currentaffair.vacancies.model.NotificationType
import com.example.studyglows.utils.UIUtils.bottomBorder
import com.example.studyglows.utils.Utils

@Composable
fun VacancyListItem(
    modifier: Modifier = Modifier,
    itemDetails: NotificationItem
) {
    val itemIcon = when(itemDetails.type) {
        NotificationType.JOB -> R.drawable.work
        NotificationType.ADMIT_CARD -> R.drawable.assignment
        NotificationType.RESULT -> R.drawable.analytics
    }
    Column(modifier = Modifier
        .bottomBorder(
            strokeWidth = 1.dp,
            color = Color(0xFFB1D4EA),
            horizontalOffset = 8.dp
        )
        .padding(bottom = 12.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier
                .background(Color(0xFF8BBFDF))
                .padding(22.dp, 4.dp)) {
                Text(
                    text = itemDetails.tag,
                    style = TextStyle(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        color = Color(0xFFE6F1F8),
                        letterSpacing = 1.5.sp,
                    )
                )
            }
        }
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(13.dp)
            ) {
                Icon(imageVector = ImageVector.vectorResource(itemIcon), contentDescription = "item type")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = itemDetails.title,
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 20.4.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.15.sp,
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                itemDetails.date?.let {
                    Text(
                        text = "Last Date: ${Utils.getDate(it, "dd/MM/yyyy")}",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            color = Color(0xFF8798AD),
                            letterSpacing = 0.4.sp,
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}