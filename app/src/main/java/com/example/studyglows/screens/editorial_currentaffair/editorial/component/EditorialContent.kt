package com.example.studyglows.screens.editorial_currentaffair.editorial.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.navArgument
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialItem

@Composable
fun EditorialContent(
    modifier: Modifier = Modifier,
    headerItem: EditorialItem?,
    onHeaderClicked: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(
            topStart = 33.dp,
            topEnd = 33.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier.fillMaxWidth(),
    ) {
        headerItem?.let {
            TopEditorialItem(
                item = it,
                modifier = Modifier.padding(16.dp).clickable { onHeaderClicked() }
            )
        }
        Spacer(modifier = Modifier.height(16.dp).padding(16.dp))
        content()
    }
}