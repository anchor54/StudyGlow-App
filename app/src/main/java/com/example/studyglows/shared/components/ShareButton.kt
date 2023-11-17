package com.example.studyglows.shared.components

import android.content.Context
import android.content.Intent
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import com.example.studyglows.R

@Composable
fun ShareButton(deeplink: String) {
    val context = LocalContext.current

    IconButton(onClick = { onShareClicked(context, deeplink) }) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.share),
            contentDescription = "Share content"
        )
    }
}

fun onShareClicked(context: Context, deeplink: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, deeplink)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}