package com.example.studyglows.screens.editorial_currentaffair.editorial.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.editorial_currentaffair.editorial.EditorialViewModel
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialItem
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun EditorialDetails(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    sharedVM: SharedViewModel,
    viewModel: EditorialViewModel
) {
    val editorialId = navHostController.currentBackStackEntry?.arguments?.getString("editorialId") ?: ""
    val editorialDetails by viewModel.editorial_details.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getEditorialDetails(editorialId)
    }

    Box(modifier = modifier) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            EditorialTopBar(
                onLogoClicked = { sharedVM.sendUIEvent(AppUIEvent.ShowDrawer()) },
                onSavedClicked = { /*TODO*/ },
                onShareClicked = { /*TODO*/ }
            )
            EditorialContent(
                headerItem = EditorialItem(
                    image = editorialDetails.image,
                    title = editorialDetails.title,
                    date = editorialDetails.date
                ),
                onHeaderClicked = {}
            ) {
                Text(
                    text = editorialDetails.subtitle,
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 22.5.sp,
                        color = Color(0xFF8798AD),
                        letterSpacing = 0.25.sp,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .scrollable(
                            rememberScrollState(),
                            Orientation.Horizontal
                        ),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    editorialDetails.author.map {
                        Row {
                            it.image?.let {
                                GlideImage(
                                    imageModel = { it },
                                    imageOptions = ImageOptions(
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.Fit
                                    ),
                                    modifier = Modifier.clip(RoundedCornerShape(100.dp))
                                )
                            } ?: kotlin.run {
                                Image(imageVector = ImageVector.vectorResource(R.drawable.account), contentDescription = "author icon")
                            }
                            Text(
                                text = "by ${it.name}",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    lineHeight = 18.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF8798AD),
                                    letterSpacing = 0.1.sp,
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = editorialDetails.content,
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 25.5.sp,
                        color = Color(0xFF2E384D),
                        letterSpacing = 0.5.sp,
                    )
                )
            }
        }
    }
}