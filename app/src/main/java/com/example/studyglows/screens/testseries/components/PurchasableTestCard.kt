package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.testseries.model.TestCardItem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchasableTestCard(
    modifier: Modifier = Modifier,
    testCardItem: TestCardItem,
    onCardClicked: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onCardClicked,
        modifier = modifier.width(width = 160.dp),
    ) {
        Spacer(modifier = Modifier.height(9.dp))
        GlideImage(
            imageModel = { testCardItem.icon },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            ),
            modifier = Modifier
                .size(width = 40.dp, height = 40.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = testCardItem.title,
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                textAlign = TextAlign.Center,
                letterSpacing = 0.15.sp,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "${testCardItem.totalTests} Total Tests | ",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 15.6.sp,
                    color = Color(0xFF8798AD),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.4.sp,
                )
            )
            Text(
                text = "${testCardItem.freeTests} Free",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 15.6.sp,
                    color = Color(0xFF105284),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.4.sp,
                )
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = testCardItem.subtitle,
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                color = Color(0xFF8798AD),
                textAlign = TextAlign.Center,
                letterSpacing = 0.4.sp,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = testCardItem.languages.joinToString(", "),
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                color = Color(0xFF8798AD),
                textAlign = TextAlign.Center,
                letterSpacing = 0.4.sp,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TestCardButton(modifier = Modifier.fillMaxWidth(), content = "₹ ${testCardItem.price}")
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewTestCard() {
    PurchasableTestCard(testCardItem = TestCardItem(
        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
        title = "SBI CLERK",
        subtitle = "Sectional Tests",
        languages = listOf("English", "Hindi"),
        totalTests = 170,
        freeTests = 3,
        price = 3000.0f
    )) {}
}