package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.example.studyglows.screens.testseries.model.QuestionResultStatus
import com.example.studyglows.screens.testseries.model.QuestionState

@Composable
fun QuestionResultStatusItem(
    index: Int,
    status: QuestionResultStatus?,
    onQuestionClicked: () -> Unit
) {
    if (status == QuestionResultStatus.UNATTEMPTED) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.error),
            contentDescription = "unattempted question",
            modifier = Modifier.size(30.dp).clickable { onQuestionClicked() }
        )
    } else {
        Box(
            modifier = Modifier
                .clickable { onQuestionClicked() }
                .size(30.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(
                    color = when (status) {
                        QuestionResultStatus.CORRECT -> Color(0xFF00D408)
                        QuestionResultStatus.WRONG -> Color(0xFFFF2C45)
                        else -> Color(0xFF025284)
                    }
                )
        ) {
            Text(
                text = (index + 1).toString(),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFFE6F1F8),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }
    }
}