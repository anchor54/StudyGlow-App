package com.example.studyglows.screens.testseries.subscreens.testscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.testseries.TestSeriesViewModel
import com.example.studyglows.shared.components.AppContentCard
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun TestWelcome(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: TestSeriesViewModel,
    sharedViewModel: SharedViewModel
) {
    val testId = navHostController.currentBackStackEntry?.arguments?.getString("testId") ?: ""
    val testDetails by viewModel.testDetails.collectAsState()
    LaunchedEffect(key1 = testId) {
        viewModel.getTestDetails(testId)
    }

    Column(
        modifier = modifier.background(Color(0xFFE6F1F8)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(vertical = 35.dp).weight(0.4f)) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.mono_logo),
                contentDescription = "Logo",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "STUDY GLOWS",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF0369A9),
                    letterSpacing = 1.69.sp,
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(9.dp))
            Text(
                text = testDetails.testSeries,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 20.4.sp,
                    color = Color(0xFF025284),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.15.sp,
                )
            )
        }
        AppContentCard(modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier
                .background(Color.White)
                .padding(16.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = testDetails.testType,
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            color = Color(0xFF8798AD),
                            letterSpacing = 0.4.sp,
                        )
                    )
                    Text(
                        text = testDetails.title,
                        style = TextStyle(
                            fontSize = 21.sp,
                            lineHeight = 25.2.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF2E384D),
                            letterSpacing = 0.15.sp,
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${testDetails.totalQuestionCount} Questions",
                            style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF2E384D),
                                letterSpacing = 0.1.sp,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.padding(vertical = 18.dp)
                        )
                        Divider(
                            color = Color(0xFFB1D4EA),
                            modifier = Modifier
                                .fillMaxHeight(0.8f)  //fill the max height
                                .width(1.dp)
                        )
                        Text(
                            text = "${testDetails.totalMarks} Marks",
                            style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF2E384D),
                                letterSpacing = 0.1.sp,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.padding(vertical = 18.dp)
                        )
                    }
                    Text(
                        text = "Description",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            color = Color(0xFF8798AD),
                            letterSpacing = 0.4.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = testDetails.description,
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 22.5.sp,
                            color = Color(0xFF2E384D),
                            letterSpacing = 0.25.sp,
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { navHostController.navigate("${Screen.TestQuestions.route}?testId=$testId") },
                    colors = ButtonDefaults.buttonColors(Color(0xFF025284)),
                    modifier = Modifier.fillMaxWidth(0.8f).align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "BEGIN",
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                            letterSpacing = 1.25.sp,
                        )
                    )
                }
            }
        }
    }
}