package com.example.studyglows.screens.courseprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studyglows.R
import com.example.studyglows.screens.home.common.components.CourseCard
import com.example.studyglows.screens.home.common.components.EducatorCard
import com.example.studyglows.screens.home.common.components.FAQItem
import com.example.studyglows.utils.Utils
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CourseProfileScreen(id: String = "") {
    val viewModel = viewModel<CourseProfileViewModel>()
    val courseDetails by viewModel.courseProfile.collectAsState()
    val similarCourses by viewModel.similarCourses.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchCourseDetails(id)
        viewModel.fetchSimilarCourses(id)
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            topbar,
            content,
            bottombar
        ) = createRefs()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFB1D4EA))
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(bottombar.top)
                },
        ) {
            GlideImage(
                imageModel = { courseDetails.imageUrl },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF025284)),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = Utils.getDiscountedPriceText(
                        originalPrice = Utils.amountWithRupeeSymbol(courseDetails.originalPrice),
                        discountedPrice =
                            if (courseDetails.discountedPrice == 0f) ""
                            else Utils.amountWithRupeeSymbol(courseDetails.discountedPrice)
                    ),
                    modifier = Modifier.padding(17.dp, 4.dp),
                    color = Color.White
                )
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Text(
                    text = courseDetails.courseTitle,
                    style = TextStyle(
                        fontSize = 25.sp,
                        lineHeight = 30.sp,
                        color = Color(0xFF2E384D),
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = courseDetails.brief,
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 15.6.sp,
                        color = Color(0xFF8798AD),
                        letterSpacing = 0.4.sp,
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "VIEW DETAILS",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        letterSpacing = 1.25.sp,
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Divider(
                    thickness = 1.dp,
                    color = Color(0xFFB1D4EA),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Educators",
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 20.4.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.15.sp,
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    courseDetails.educators.map { educator ->
                        EducatorCard(
                            name = educator.educatorName,
                            imageUrl = educator.imageUrl,
                            achievements = educator.achievements,
                            modifier = Modifier
                                .fillMaxHeight()
                                .widthIn(max = 300.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(36.dp))
                if (courseDetails.faqs.isNotEmpty()) {
                    Text(
                        text = "Frequently Asked Questions",
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 20.4.sp,
                            color = Color(0xFF2E384D),
                            letterSpacing = 0.15.sp,
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        courseDetails.faqs.map { faq ->
                            FAQItem(faq.question, faq.answer)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Text(
                    text = "Similar Courses",
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 20.4.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.15.sp,
                    )
                )
                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    similarCourses.map { course ->
                        CourseCard(
                            imageUrl = course.imageUrl ?: "",
                            courseName = course.title ?: "",
                            originalPrice = Utils.amountWithRupeeSymbol(course.originalPrice ?: 0f),
                            discountedPrice = Utils.amountWithRupeeSymbol(course.discountedPrice ?: 0f),
                            purchased = course.isBought ?: false,
                            tagText = course.tag ?: ""
                        )
                    }
                }
            }
        }
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF025284)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottombar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Text(text = "PURCHASE")
        }
        TopAppBar(
            backgroundUrl = courseDetails.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(topbar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun TopAppBar(
    backgroundUrl: String = "",
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (backgroundUrl.isNotEmpty()) {
            GlideImage(
                modifier = Modifier.height(45.dp).fillMaxWidth(),
                imageModel = { backgroundUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                ),
                component = rememberImageComponent {
                    +BlurTransformationPlugin(radius = 100)
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
            Row {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.ShoppingCart, null)
                }
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Outlined.Favorite, null)
                }
            }
        }
    }
}