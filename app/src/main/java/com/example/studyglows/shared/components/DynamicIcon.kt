package com.example.studyglows.shared.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DynamicIcon(
    modifier: Modifier = Modifier,
    icon: String = ""
) {
    GlideImage(
        modifier = modifier,
        imageModel = { icon },
        imageOptions = ImageOptions(
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit
        )
    )
}