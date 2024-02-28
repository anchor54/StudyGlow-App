package com.example.studyglows.screens.home.courseprofile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.home.HomeViewModel
import com.example.studyglows.screens.home.common.components.CourseCard
import com.example.studyglows.screens.home.common.components.EducatorCard
import com.example.studyglows.screens.home.common.components.FAQItem
import com.example.studyglows.screens.home.common.models.Course
import com.example.studyglows.screens.home.common.models.CourseFeature
import com.example.studyglows.screens.home.common.models.CourseProfileModel
import com.example.studyglows.screens.home.common.models.Educators
import com.example.studyglows.screens.home.common.models.FAQ
import com.example.studyglows.utils.Utils
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CourseDetailsScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val courseId = navHostController.currentBackStackEntry?.arguments?.getString("courseId") ?: ""
    val courseDetails by viewModel.courseProfile.collectAsState()
    val similarCourses by viewModel.similarCourses.collectAsState()
    val educatorString by remember(courseDetails.educators) {
        derivedStateOf {
            courseDetails.educators?.let {
                if (it.size > 2)
                    "${it[0].educatorName}, ${it[1].educatorName} and ${it.size - 2} more."
                else if (it.size > 1)
                    "${it[0].educatorName} and ${it[1].educatorName}"
                else if (it.isNotEmpty())
                    it[0].educatorName
                else ""
            } ?: run { "" }
        }
    }

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
        CourseDetailsContent(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(content) {
                    top.linkTo(topbar.bottom, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(bottombar.top, margin = 120.dp)
                },
            courseDetails = courseDetails,
            educatorString = educatorString,
            similarCourses = similarCourses,
            navHostController = navHostController
        )
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
        BottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottombar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            onPurchaseClicked = { viewModel.addToCart(courseId) },
            onViewContentClicked = {
                navHostController.navigate(
                    Screen.Lecture.route + "?courseId=$courseId"
                )
            }
        )
    }
}

@Composable
fun CourseDetailsContent(
    modifier: Modifier = Modifier,
    courseDetails: CourseProfileModel,
    educatorString: String,
    similarCourses: List<Course>,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFE6F1F8)),
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
                    originalPrice = Utils.amountWithRupeeSymbol(courseDetails.originalPrice ?: 0f),
                    discountedPrice =
                    if (courseDetails.discountedPrice == 0f) ""
                    else Utils.amountWithRupeeSymbol(courseDetails.discountedPrice ?: 0f)
                ),
                modifier = Modifier.padding(17.dp, 4.dp),
                color = Color.White
            )
        }
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.account),
                tint = Color(color = 0xFF56A1D1),
                contentDescription = "teachers"
            )
            Box(modifier = Modifier.width(10.dp))
            Text(
                text = educatorString,
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF8798AD),
                    letterSpacing = 0.1.sp,
                )
            )
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Text(
                text = courseDetails.courseTitle ?: "",
                style = TextStyle(
                    fontSize = 25.sp,
                    lineHeight = 30.sp,
                    color = Color(0xFF2E384D),
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = courseDetails.brief ?: "",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 15.6.sp,
                    color = Color(0xFF8798AD),
                    letterSpacing = 0.4.sp,
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            CourseFeatureSection(courseFeatures = courseDetails.features ?: listOf())
            Spacer(modifier = Modifier.height(16.dp))
            CourseEducatorsSection(courseEducators = courseDetails.educators ?: listOf())
            Spacer(modifier = Modifier.height(36.dp))
            if (!courseDetails.faqs.isNullOrEmpty()) {
                CourseFAQSection(courseFAQs = courseDetails.faqs)
            }
            SimilarCourseSection(similarCourses = similarCourses) {
                navHostController.navigate(
                    Screen.CourseDetails.route + "?courseId=$it"
                )
            }
            Spacer(modifier = Modifier.height(36.dp))
        }
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
        .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
                    tint = Color(0xFF023F66),
                    contentDescription = "go back"
                )
            }
            Row {
                IconButton(onClick = onCartClicked) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.shopping_cart_inactive),
                        tint = Color(0xFF023F66),
                        contentDescription = "add to cart"
                    )
                }
                IconButton(onClick = onFavouriteClicked) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.bookmark),
                        tint = Color(0xFF023F66),
                        contentDescription = "add to cart"
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onPurchaseClicked: () -> Unit,
    onViewContentClicked: () -> Unit
) {
    Row(modifier = modifier) {
        Button(
            onClick = onPurchaseClicked,
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
            onClick = onViewContentClicked,
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
}

@Composable
fun SimilarCourseSection(
    modifier: Modifier = Modifier,
    similarCourses: List<Course>,
    onCourseClicked: (String) -> Unit
) {
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
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(similarCourses.size) {
            CourseCard(courseDetails = similarCourses[it], onClicked = onCourseClicked)
        }
    }
}

@Composable
fun CourseFAQSection(
    modifier: Modifier = Modifier,
    courseFAQs: List<FAQ>
) {
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
        courseFAQs.map { faq ->
            FAQItem(faq.question, faq.answer)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun CourseEducatorsSection(
    modifier: Modifier = Modifier,
    courseEducators: List<Educators>
) {
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
        courseEducators.map { educator ->
            EducatorCard(
                name = educator.educatorName,
                imageUrl = educator.imageUrl,
                achievements = educator.achievements.split(".").map { it.trim() },
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = 300.dp)
            )
        }
    }
}

@Composable
fun CourseFeatureSection(
    modifier: Modifier = Modifier,
    courseFeatures: List<CourseFeature>
) {
    Text(
        text = "Course Features",
        style = TextStyle(
            fontSize = 17.sp,
            lineHeight = 20.4.sp,
            color = Color(0xFF2E384D),
            letterSpacing = 0.15.sp,
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
    CourseFeaturesCard(courseFeatures = courseFeatures)
}

@Composable
fun CourseFeaturesCard(
    modifier: Modifier = Modifier,
    courseFeatures: List<CourseFeature>
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
        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            courseFeatures.map { 
                CourseFeatureItem(courseFeature = it)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CourseFeatureItem(
    modifier: Modifier = Modifier,
    courseFeature: CourseFeature
) {
    @DrawableRes val resId = when(courseFeature.id) {
        1L -> R.drawable.file_active
        2L -> R.drawable.calendar
        else -> R.drawable.file_active
    }
    Row {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEEF4F8)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Icon(
                modifier = Modifier.padding(8.dp),
                imageVector = ImageVector.vectorResource(id = resId),
                contentDescription = "feature point"
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = courseFeature.title,
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF3C4852),
                letterSpacing = 0.4.sp,
            )
        )
    }
}