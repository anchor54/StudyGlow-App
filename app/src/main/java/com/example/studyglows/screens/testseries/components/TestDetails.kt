package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.testseries.model.QuestionState
import com.example.studyglows.shared.model.CategorizedMap

@Composable
fun TestDetails(
    modifier: Modifier = Modifier,
    questions: CategorizedMap<String, QuestionState>,
    currentPage: Int,
    updatePage: (Int) -> Unit,
    onQuestionClicked: (idx: Int) -> Unit,
    onSubmit: () -> Unit
) {

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        TestLegend(modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp))
        Divider(thickness = 1.dp, color = Color(0xFFB1D4EA))
        QuestionMap(
            questionList = questions,
            currentPage = currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
            updateCurrentPage = updatePage
        ) { questionIdx, questionState ->
            Box(
                modifier = Modifier
                    .clickable { onQuestionClicked(questionIdx) }
                    .size(30.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        color = when (questionState) {
                            is QuestionState.AttemptedQuestion -> Color(0xFF00D408)
                            is QuestionState.UnAttemptedQuestion -> Color(0xFFFF2C45)
                            is QuestionState.ReviewedQuestion -> Color(0xFFF7E702)
                            else -> Color(0xFF025284)
                        }
                    )
            ) {
                Text(
                    text = (questionIdx + 1).toString(),
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
        Button(
            onClick = onSubmit,
            colors = ButtonDefaults.buttonColors(Color(0xFF025284)),
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "SUBMIT TEST",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.25.sp,
                )
            )
        }
    }
}