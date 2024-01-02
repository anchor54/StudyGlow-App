package com.example.studyglows.screens.home.courseprofile

import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.common.components.CourseCard
import com.example.studyglows.screens.home.common.components.EducatorCard
import com.example.studyglows.screens.home.common.components.FAQItem
import com.example.studyglows.utils.UIUtils
import com.example.studyglows.utils.Utils
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin

@Composable
fun CourseDetailsScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val courseId = navHostController.currentBackStackEntry?.arguments?.getString("courseId") ?: ""
    val courseDetails by viewModel.courseProfile.collectAsState()
    val similarCourses by viewModel.similarCourses.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchCourseDetails(courseId)
        viewModel.fetchSimilarCourses(courseId)

        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeUIEvent.AddToCartSuccess -> {
                    navHostController.navigate(Route.CART_ROUTE.name)
                }
                else -> {}
            }
        }
    }

    ConstraintLayout(modifier = modifier) {
        val (
            topbar,
            content,
            bottombar
        ) = createRefs()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFE6F1F8))
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(bottombar.top, margin = 40.dp)
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
                Spacer(modifier = Modifier.height(16.dp))
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
                        CourseCard(course) {
                            navHostController.navigate(
                                Screen.CourseDetails.route +
                                "?courseId=$it"
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottombar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Button(
                onClick = { viewModel.addToCart(courseId) },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF025284)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "PURCHASE",
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 12.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFE6F1F8),
                        letterSpacing = 1.25.sp,
                    )
                )
            }
            Button(
                onClick = {
                    navHostController.navigate(
                        Screen.Lecture.route +
                        "?courseId=$courseId"
                    )
                },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE6F1F8)
                )
            ) {
                Text(
                    text = "VIEW CONTENT",
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 12.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        letterSpacing = 1.25.sp,
                    )
                )
            }
        }
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(topbar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onBackPressed = { navHostController.popBackStack() }
        )
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onCartClicked: () -> Unit = {},
    onFavouriteClicked: () -> Unit = {}
) {
    Box(modifier = modifier
        .background(color = Color.Black.copy(alpha = 0.75f))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Filled.ArrowBack, null)
            }
            Row {
                IconButton(onClick = onCartClicked) {
                    Icon(Icons.Filled.ShoppingCart, null)
                }
                IconButton(onClick = onFavouriteClicked) {
                    Icon(Icons.Outlined.Favorite, null)
                }
            }
        }
    }
}