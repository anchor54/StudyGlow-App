package com.example.studyglows.screens.home.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studyglows.R
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.shared.components.TagChip
import com.example.studyglows.utils.Utils

@Composable
fun CourseCard(
    courseDetails: Course,
    modifier: Modifier = Modifier,
    onClicked: (String) -> Unit = {}
) {
    val originalPrice = Utils.amountWithRupeeSymbol(courseDetails.originalPrice)
    val discountedPrice = Utils.amountWithRupeeSymbol(courseDetails.discountedPrice)
    val coursePriceText = Utils.getDiscountedPriceText(originalPrice, discountedPrice)

    val cartImage = ImageVector.vectorResource(
        id = if (courseDetails.isBought) R.drawable.check
        else R.drawable.shopping_cart_active
    )

    CourseContainer(
        imageUrl = courseDetails.imageUrl,
        title = AnnotatedString(courseDetails.title),
        subtitle = coursePriceText,
        modifier = modifier,
        onClicked = onClicked
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            TagChip(courseDetails.tag)
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
        Course(
            imageUrl = "https://d2v3ngkpxqk4xk.cloudfront.net/media/course/thumbnail/20230714/PUBLIC_SPEAKING_COURSE.png",
            title = "Public Speaking",
            originalPrice = 1000f,
            discountedPrice = 400f,
            isBought = true,
            tag = "Popular"
        )
    )
}