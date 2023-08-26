package com.example.studyglows.screens.editorial_currentaffair.current_affairs.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.CurrentAffairsViewModel
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairDetails
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CurrentAffairDetailContent(
    modifier: Modifier = Modifier,
    viewModel: CurrentAffairsViewModel,
    id: String
) {
    val details by viewModel.currentAffairDetails.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getCurrentAffairDetails(id)
    }
    Column(modifier = modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {
        GlideImage(
            imageModel = { details.image },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            ),
            modifier = Modifier.width(153.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = details.title,
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column {
            details.contents.map { BulletPoint(text = it) }
        }
    }
}
