package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun QuestionsScreenOverlay(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .background(Color(0x80000000))
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 45.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.direction_arrow_left), contentDescription = "Left direction")
                    Text(
                        text = "Tap on Question no. to see your overall progress",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.4.sp,
                        ),
                        modifier = Modifier.width(72.dp)
                    )
                }
                Column {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.direction_arrow_straight), contentDescription = "straight direction")
                    Text(
                        text = "Tap on Section Name no. to see all the details",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.4.sp,
                        ),
                        modifier = Modifier.width(72.dp)
                    )
                }
                Column {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.direction_arrow_right), contentDescription = "Right direction")
                    Text(
                        text = "Time left",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.4.sp,
                        ),
                        modifier = Modifier.width(72.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(75.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.swipe_right), contentDescription = "Swipe Right")
                    Text(
                        text = "Swipe to go to the next question",
                        style = TextStyle(
                            fontSize = 21.sp,
                            lineHeight = 25.2.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.15.sp,
                        )
                    )
                }
            }
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(
                text = "Got it!",
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
    }
}