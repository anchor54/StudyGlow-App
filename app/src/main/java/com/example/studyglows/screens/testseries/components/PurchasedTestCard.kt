package com.example.studyglows.screens.testseries.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.testseries.model.PurchasedTestItem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchasedTestCard(
    modifier: Modifier = Modifier,
    testCardItem: PurchasedTestItem,
    onCardClicked: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier.width(width = 160.dp),
        onClick = onCardClicked
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
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
            Text(
                text = "${testCardItem.totalTests} Total Tests",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 15.6.sp,
                    color = Color(0xFF8798AD),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.4.sp,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            LinearProgressIndicator(
                progress = testCardItem.getProgress(),
                color = Color(0xFF01304E),
                trackColor = Color(0xFFE6F1F8),
                modifier = Modifier
                    .width(154.dp)
                    .height(10.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "${testCardItem.completedTests}/${testCardItem.totalTests}",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 15.6.sp,
                    color = Color(0xFF025284),
                    letterSpacing = 0.4.sp,
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewPurchasedTestCard() {
    PurchasedTestCard(testCardItem = PurchasedTestItem(
        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
        title = "SBI CLERK",
        totalTests = 170,
        completedTests = 13
    )) {}
}