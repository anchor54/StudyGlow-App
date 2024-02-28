package com.example.studyglows.screens.test.subscreens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun TestEducationScreen(modifier: Modifier = Modifier, onClose: () -> Unit) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color(0x6601304E))) {
        Column(
            modifier = Modifier.padding(top = 75.dp).fillMaxHeight().fillMaxWidth(0.9f).align(Alignment.TopCenter),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TestEducationDirection(icon = R.drawable.education_arrow_left, text = "Tap on Question no. to see your overall progress")
                TestEducationDirection(icon = R.drawable.education_arrow_straight, text = "Tap on Section Name no. to see all the details")
                TestEducationDirection(icon = R.drawable.education_arrow_right, text = "Time left")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.swipe_right),
                    tint = Color.White,
                    contentDescription = "swipe right"
                )
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
            Button(
                onClick = onClose,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
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
}

@Composable
fun TestEducationDirection(
    @DrawableRes icon: Int,
    text: String
) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            tint = Color.White,
            contentDescription = "direction"
        )
        Box(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.width(72.dp),
            text = text,
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                letterSpacing = 0.4.sp,
            )
        )
    }
}