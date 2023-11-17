package com.example.studyglows.shared.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.studyglows.R

@Composable
fun SaveIcon(
    onSavedClicked: () -> Unit
) {
    var saved by remember { mutableStateOf(false) }
    IconButton(onClick = {
        saved = !saved
        onSavedClicked()
    }) {
        Icon(
            imageVector = ImageVector.vectorResource(
                if (saved) R.drawable.saved else R.drawable.bookmark
            ),
            contentDescription = "Save Item",
        )
    }
}