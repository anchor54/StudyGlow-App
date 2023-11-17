package com.example.studyglows.screens.test.subscreens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.navigation.TestSeriesPathCreator
import com.example.studyglows.screens.auth.common.models.TestUIEvent
import com.example.studyglows.screens.test.TestViewModel
import com.example.studyglows.screens.testseries.components.QuestionComponent
import com.example.studyglows.screens.testseries.components.BaseTestCard
import com.example.studyglows.screens.testseries.components.TestAttemptedDialog
import com.example.studyglows.screens.testseries.components.TestDetails
import com.example.studyglows.screens.testseries.components.TestSubmitDialog
import com.example.studyglows.screens.testseries.model.QuestionState
import com.example.studyglows.screens.testseries.model.QuestionType
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.example.studyglows.utils.Utils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TestQuestionsScreen(
    modifier: Modifier = Modifier,
    viewModel: TestViewModel,
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    val testId = navHostController.currentBackStackEntry?.arguments?.getString("testId") ?: ""
    val bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded, skipHiddenState = false)
    val questions by viewModel.questions.collectAsState()
    val testDetails by viewModel.testDetails.collectAsState()
    val questionState by viewModel.questionState.collectAsState()
    val categoryIdx by viewModel.currentCategoryIdx.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val categoryQuestionIdxs by viewModel.currentQuestionIdxs.collectAsState()
    val currQuestionIdx by remember(categoryIdx) {
        derivedStateOf { categoryQuestionIdxs[categoryIdx] }
    }
    var openSubmitDialog by remember { mutableStateOf(false) }
    var openAttemptsDialog by remember { mutableStateOf(false) }
    val categoryPagerState = rememberPagerState(initialPage = categoryIdx)
    val timeUntil by remember { mutableStateOf(System.currentTimeMillis() + testDetails.duration) }
    var timeRemaining by remember { mutableStateOf(testDetails.duration) }
    var shouldCountDown by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    fun onSubmitTest() {
        viewModel.submitTest()
        openAttemptsDialog = true
        openSubmitDialog = false
        shouldCountDown = false
    }

    fun onAttemptsDismiss() {
        openAttemptsDialog = false
        viewModel.sendUIEvent(TestUIEvent.OpenTestResultScreen(testId))
    }

    BackHandler {
        openSubmitDialog = true
    }

    LaunchedEffect(key1 = categoryIdx) {
        viewModel.getCategoryQuestions(categoryIdx)
        if (categoryPagerState.currentPage != categoryIdx) {
            categoryPagerState.animateScrollToPage(categoryIdx)
        }
    }

    LaunchedEffect(key1 = timeUntil, key2 = shouldCountDown) {
        while (shouldCountDown && timeRemaining > 0) {
            delay(1.seconds)
            timeRemaining = 0L.coerceAtLeast(timeUntil - System.currentTimeMillis())
        }
        onSubmitTest()
    }

    if (openSubmitDialog) {
        val allCategoryQuestionMap = questionState.getAllCategories().map { questionState.getMapForCategory(it) }
        val allCategoryQuestions = allCategoryQuestionMap.flatMap { it.values }
        val attemptedQuestions = allCategoryQuestions.filterIsInstance<QuestionState.AttemptedQuestion>()
        TestSubmitDialog(
            totalQuestions = allCategoryQuestions.size,
            attemptedQuestions = attemptedQuestions.size,
            onDismiss = { openSubmitDialog = false },
            onConfirm = { onSubmitTest() }
        )
    }

    if (openAttemptsDialog) {
        val questionsAttemptedMap = categories.associateWith {
            val attempted = questionState.getMapForCategory(it).count { it.value is QuestionState.AttemptedQuestion }
            val total = questionState.getMapForCategory(it).size
            Pair(attempted, total)
        }
        Dialog(onDismissRequest = { onAttemptsDismiss() }) {
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(15.dp)
            ) {
                TestAttemptedDialog(
                    onDismiss = { onAttemptsDismiss() },
                    questionsAttempted = questionsAttemptedMap
                )
            }
        }
    }

    Column(modifier = modifier.background(Color(0xFFE6F1F8))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BaseTestCard {
                Text(
                    text = "${currQuestionIdx + 1}/${testDetails.categoryQuestionCount(categories[categoryIdx])}",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.4.sp,
                    ),
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            BaseTestCard {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { viewModel.changeCategory(categoryIdx - 1) },
                        enabled = categoryIdx > 0 && categoryIdx < testDetails.categories,
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.arrow_prev),
                            contentDescription = "Go to Prev",
                            tint =
                                if (categoryIdx == 0) Color(0xFFB1D4EA)
                                else Color(0xFF025284)
                        )
                    }
                    HorizontalPager(
                        pageCount = categories.size,
                        state = categoryPagerState,
                        modifier = Modifier.width(110.dp),
                        userScrollEnabled = false
                    ) {
                        Text(
                            text = categories[it],
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
                        onClick = { viewModel.changeCategory(categoryIdx + 1) },
                        enabled = categoryIdx >= 0 && categoryIdx < testDetails.categories - 1,
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.arrow_next),
                            contentDescription = "Go to Next",
                            tint =
                            if (categoryIdx == testDetails.categories - 1) Color(0xFFB1D4EA)
                            else Color(0xFF025284)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            BaseTestCard {
                Text(
                    text = Utils.getTimeInMinsAndSecs(timeRemaining / 1000),
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.4.sp,
                    ),
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            TestDetails(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                questions = questionState,
                currentPage = categoryIdx,
                updatePage = { viewModel.changeCategory(it) },
                onQuestionClicked = {
                    viewModel.changeQuestion(it)
                    coroutineScope.launch {
                        bottomSheetState.expand()
                    }
                },
                onSubmit = { openSubmitDialog = true }
            )
            BottomSheetScaffold(
                scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState),
                sheetDragHandle = null,
                sheetContent = {
                    QuestionComponent(
                        modifier = modifier.fillMaxSize(),
                        questions = questions,
                        currentQuestionIdx = currQuestionIdx,
                        onQuestionChange = { viewModel.changeQuestion(it) },
                        showQuestion = bottomSheetState.currentValue == SheetValue.Expanded,
                        onAnswered = { id, answer, type ->
                            viewModel.addAnswer(id, answer, QuestionType.getQuestionType(type))
                        },
                        toggleShowState = {
                            coroutineScope.launch {
                                if (bottomSheetState.currentValue == SheetValue.Expanded) bottomSheetState.partialExpand()
                                else bottomSheetState.expand()
                            }
                        },
                        getQuestionDetails = { viewModel.getQuestionDetails(categories[categoryIdx], it) }
                    )
                },
                sheetContainerColor = Color.White,
                sheetPeekHeight = 80.dp,
            ) {}
        }
    }

}

