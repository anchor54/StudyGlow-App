package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.example.studyglows.screens.testseries.model.QuestionResultStatus
import com.example.studyglows.shared.components.VerticalGrid
import com.example.studyglows.shared.model.CategorizedMap

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestResultQuestionMap(
    questionList: CategorizedMap<String, QuestionResultStatus?>
) {
    var currentPage by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = currentPage)

    LaunchedEffect(key1 = currentPage) {
        pagerState.animateScrollToPage(currentPage)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { currentPage-- },
                enabled = currentPage > 0 && currentPage < questionList.size(),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.double_arrow_left),
                    contentDescription = "previous",
                    tint =
                    if (currentPage == 0) Color(0xFFB1D4EA)
                    else Color(0xFF025284)
                )
            }
            HorizontalPager(
                pageCount = questionList.size(),
                state = pagerState,
                modifier = Modifier.width(110.dp),
                userScrollEnabled = false
            ) {
                Text(
                    text = questionList.getAllCategories()[it],
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.4.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
            IconButton(
                onClick = { currentPage++ },
                enabled = currentPage >= 0 && currentPage < questionList.size() - 1,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.double_arrow_right),
                    contentDescription = "next",
                    tint =
                    if (currentPage == questionList.size() - 1) Color(0xFFB1D4EA)
                    else Color(0xFF025284)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalPager(
            pageCount = questionList.size(),
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = false
        ) {
            val currentCategory = questionList.getAllCategories()[it]
            val questionState = questionList.getMapForCategory(currentCategory)
            val questionIndexStateMap =
                questionState.map { it.value }.withIndex().map { it.index to it.value }
            VerticalGrid(
                items = questionIndexStateMap,
                columns = 7,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                QuestionResultStatusItem(it.first, it.second) {}
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF025284)
                ),
                shape = RoundedCornerShape(100.dp),
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = "DONE",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        textAlign = TextAlign.Center,
                        letterSpacing = 1.25.sp,
                    )
                )
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(0xFF025284)),
                shape = RoundedCornerShape(100.dp),
                modifier = Modifier.weight(0.3f)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.share),
                    contentDescription = "share results"
                )
            }
        }
    }
}

