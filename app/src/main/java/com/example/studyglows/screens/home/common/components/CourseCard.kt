package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.example.studyglows.shared.components.TagChip
import com.example.studyglows.utils.Utils

@Composable
fun CourseCard(
    imageUrl: String,
    courseName: String,
    originalPrice: String,
    discountedPrice: String,
    purchased: Boolean = false,
    tagText: String = "",
    modifier: Modifier = Modifier
) {
    val coursePriceText = Utils.getDiscountedPriceText(originalPrice, discountedPrice)

    val cartImage = ImageVector.vectorResource(
        id = if (purchased) R.drawable.check else R.drawable.shopping_cart
    )

    CourseContainer(
        imageUrl = imageUrl,
        title = AnnotatedString(courseName),
        subtitle = coursePriceText,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Spacer(modifier = Modifier.height(7.dp))
                TagChip(tagText)
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB1D4EA)
                ),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 4.dp,
                    bottomStart = 4.dp
                )
            ) {
                Image(
                    imageVector = cartImage,
                    contentDescription = "Cart icon",
                    modifier = Modifier.padding(6.dp, 4.dp, 2.dp, 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseCard() {
    CourseCard(
        imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
        courseName = "Public Speaking",
        originalPrice = "1000",
        discountedPrice = "400",
        purchased = true,
        tagText = "Popular"
    )
}