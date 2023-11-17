package com.example.studyglows.screens.editorial_currentaffair.editorial.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialItem

@Composable
fun EditorialContent(
    modifier: Modifier = Modifier,
    headerItem: EditorialItem?,
    onHeaderClicked: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
        headerItem?.let {
            EditorialHeader(
                item = it,
                modifier = Modifier
                    .clickable { onHeaderClicked() }
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        content()
    }
}