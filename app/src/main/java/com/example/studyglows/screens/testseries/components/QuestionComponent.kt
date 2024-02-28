package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.studyglows.screens.testseries.model.QuestionItem
import com.example.studyglows.screens.testseries.model.QuestionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun QuestionsComponent(
    modifier: Modifier = Modifier,
    questions: List<QuestionItem?>,
    currentQuestionIdx: Int,
    onQuestionChange: (Int) -> Unit,
    showQuestion: Boolean,
    toggleShowState: () -> Unit,
    content: @Composable (
        questionIdx: Int,
        questionItem: QuestionItem
    ) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = currentQuestionIdx)

    LaunchedEffect(key1 = currentQuestionIdx) {
        if (pagerState.currentPage != currentQuestionIdx) {
            pagerState.scrollToPage(currentQuestionIdx)
        }
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        if (pagerState.currentPage != currentQuestionIdx) {
            onQuestionChange(pagerState.currentPage)
        }
    }

    Column(
        modifier = modifier.padding(horizontal = 17.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(onClick = toggleShowState) {
                Icon(
                    imageVector = ImageVector.vectorResource(if (showQuestion) R.drawable.swipe_down else R.drawable.swipe_up),
                    contentDescription = "Swipe Down",
                    tint = Color(0xFF8BBFDF)
                )
            }
            Text(
                text = "Swipe ${if (showQuestion) "down" else "up"} to see ${if (showQuestion) "details" else "question"}",
                style = TextStyle(
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    color = Color(0xFF358FC8),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.5.sp,
                )
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        HorizontalPager(pageCount = questions.size, state = pagerState) {
            val questionItem by remember { mutableStateOf(questions[it]) }
            questionItem?.let { question -> content(it, question) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionDetail(
    questionIdx: Int,
    questionItem: QuestionItem,
    onAnswered: (questionId: String, answer: String?, type: String) -> Unit,
    getQuestionDetails: suspend (index: Int) -> Response<QuestionItem>
) {
//    LaunchedEffect(key1 = questionItem) {
//        if (questionItem == null) {
//            coroutineScope.launch(Dispatchers.IO) {
//                val response = getQuestionDetails(it)
//                if (response.isSuccessful) {
//                    response.body()?.let { questionItem = it }
//                }
//            }
//        }
//    }
    var debouncedJob: Job? = null
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Q ${questionIdx + 1}. ${questionItem.questionText}",
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 22.5.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.25.sp,
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 25.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            questionItem?.options?.mapIndexed { idx, option ->
                Text(
                    text = "(${(idx + 'a'.code).toChar()}) $option",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 22.5.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.25.sp,
                    )
                )
            }
        }
        when (questionItem?.type) {
            QuestionType.DESCRIPTIVE.type -> {
                var answer by remember { mutableStateOf("") }
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(start = 15.dp, top = 10.dp, end = 15.dp)
                        .background(Color.White, RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    value = answer,
                    onValueChange = {
                        answer = it
                        debouncedJob?.cancel()
                        debouncedJob = coroutineScope.launch(Dispatchers.IO) {
                            delay(3000)
                            onAnswered(
                                questionItem?.questionId ?: "",
                                if (it.isNullOrEmpty()) null else it,
                                questionItem?.type ?: "")
                        }
                    },
                    maxLines = 3,
                )
            }
            QuestionType.MULTIPLE_CHOICE.type,
            QuestionType.SINGLE_CHOICE.type -> {
                var answer by remember { mutableStateOf(listOf<Int>()) }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    questionItem?.options?.let {
                        List(it.size) { idx ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(10.dp),
                                shape = RoundedCornerShape(100.dp),
                                colors = CardDefaults.cardColors(
                                    if (answer.contains(idx)) Color(0xFFB1D4EA) else Color.White
                                ),
                                onClick = {
                                    onAnswered(
                                        questionItem?.questionId ?: "",
                                        (idx + 'a'.code).toChar().toString(),
                                        questionItem?.type ?: ""
                                    )
                                    answer = if (answer.contains(idx))
                                        answer.filter { it != idx }
                                    else answer + idx
                                }
                            ) {
                                Text(
                                    text = "(${(idx + 'a'.code).toChar()})",
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        lineHeight = 18.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF2E384D),
                                        textAlign = TextAlign.Center,
                                        letterSpacing = 0.1.sp,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}