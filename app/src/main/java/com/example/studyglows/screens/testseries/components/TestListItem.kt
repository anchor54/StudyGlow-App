package com.example.studyglows.screens.testseries.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.example.studyglows.screens.testseries.model.TestItem
import com.example.studyglows.utils.UIUtils.bottomBorder
import com.example.studyglows.utils.Utils
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TestListItem(
    modifier: Modifier = Modifier,
    itemDetails: TestItem
) {
    Column(modifier = Modifier
        .bottomBorder(
            strokeWidth = 1.dp,
            color = Color(0xFFB1D4EA),
            horizontalOffset = 8.dp
        )
        .padding(bottom = 12.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier
                .background(Color(0xFF8BBFDF))
                .padding(22.dp, 4.dp)) {
                Text(
                    text = itemDetails.tag,
                    style = TextStyle(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        color = Color(0xFFE6F1F8),
                        letterSpacing = 1.5.sp,
                    )
                )
            }
        }
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            GlideImage(
                imageModel = { itemDetails.icon },
                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit
                ),
                modifier = Modifier
                    .size(width = 43.dp, height = 43.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = itemDetails.title,
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 20.4.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.15.sp,
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "${itemDetails.questions} Questions",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            color = Color(0xFF8798AD),
                            letterSpacing = 0.4.sp,
                        )
                    )
                    Row {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.duration),
                            contentDescription = "test duration",
                            tint = Color(0xFF8BBFDF)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "${itemDetails.duration} Minutes",
                            style = TextStyle(
                                fontSize = 13.sp,
                                lineHeight = 15.6.sp,
                                color = Color(0xFF8798AD),
                                letterSpacing = 0.4.sp,
                            )
                        )
                    }
                    itemDetails.marks?.let {
                        Text(
                            text = "$it Marks",
                            style = TextStyle(
                                fontSize = 13.sp,
                                lineHeight = 15.6.sp,
                                color = Color(0xFF8798AD),
                                letterSpacing = 0.4.sp,
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}

@Preview
@Composable
fun PreviewTestListItem() {
    TestListItem(itemDetails = TestItem(
        icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/SBI-logo.svg/1000px-SBI-logo.svg.png?20200329171950",
        title = "SBI Clerk Prelims- Mock Test 1",
        duration = 60,
        questions = 80,
        tag = "FREE TEST",
        marks = 10
    ))
}