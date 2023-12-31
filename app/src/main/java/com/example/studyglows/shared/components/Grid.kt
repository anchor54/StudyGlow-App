package com.example.studyglows.shared.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun<T> VerticalGrid(
    columns: Int = 1,
    items: List<T>,
    rowSpacing: Dp = 0.dp,
    columnSpacing: Dp = 0.dp,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    modifier: Modifier = Modifier,
    content: @Composable (item: T) -> Unit
) {
    val itemCount = items.size
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement
    ) {
        var rows = (itemCount / columns)
        if (itemCount.mod(columns) > 0) {
            rows += 1
        }

        for (rowId in 0 until rows) {
            val firstIndex = rowId * columns
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = horizontalArrangement
            ) {
                for (columnId in 0 until columns) {
                    val index = firstIndex + columnId
                    Box(modifier = Modifier.weight(1f)) {
                        if (index < itemCount) {
                            content(items[index])
                        }
                    }

                    if (columnId < columns - 1) {
                        Spacer(modifier = Modifier.width(columnSpacing))
                    }
                }
            }

            if (rowId < rows - 1) {
                Spacer(modifier = Modifier.height(rowSpacing))
            }
        }
    }
}