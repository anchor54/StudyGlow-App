package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun EducatorCard(
    name: String = "",
    imageUrl: String = "",
    achievements: List<String> = listOf(),
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0xFFB1D4EA),
                shape = RoundedCornerShape(14.dp)
            )
            .clip(shape = RoundedCornerShape(14.dp))
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                GlideImage(
                    imageModel = { imageUrl },
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(18.dp))
                Column {
                    Text(
                        text = name,
                        style = TextStyle(
                            fontSize = 14.09.sp,
                            lineHeight = 16.91.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF2E384D),
                            letterSpacing = 0.09.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Faculty",
                        style = TextStyle(
                            fontSize = 12.21.sp,
                            lineHeight = 14.66.sp,
                            color = Color(0xFF8798AD),
                            letterSpacing = 0.38.sp,
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth()
            ) {
                achievements.map {
                    EducatorBioItem(bioText = it)
                }
            }
        }
    }
}

@Composable
fun EducatorBioItem(
    bioText: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.school),
            contentDescription = "bio point"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = bioText,
            style = TextStyle(
                fontSize = 12.21.sp,
                lineHeight = 14.66.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.38.sp,
            ),
            modifier = Modifier.fillMaxWidth(),
            softWrap = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEducatorCard() {
    EducatorCard(
        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/instructor/20220728/Inderjeet_Singh.png",
        name = "Prateek Badola",
        achievements = listOf(
            "Attempted UPSC CSE Mains twice.",
            "Attempted UPSC CSE Mains twice.",
            "Attempted UPSC CSE Mains twice.",
            "Attempted UPSC CSE Mains twice."
        ),
        modifier = Modifier.fillMaxWidth()
    )
}