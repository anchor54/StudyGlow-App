package com.example.studyglows.shared.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.example.studyglows.screens.home.common.models.TabRowItem
import com.example.studyglows.shared.model.CategorizedList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilterBottomSheet(
    filters: CategorizedList<String>,
    selectedFilters: CategorizedList<String>,
    modifier: Modifier = Modifier,
    onCancelled: () -> Unit,
    isSingleSelection: Boolean = false,
    onFilterApplied: (selectedFilters: CategorizedList<String>) -> Unit,
) {
    var filtersApplied by remember { mutableStateOf(selectedFilters) }
    val tabRowItems = filters.getAllCategories().map { filterCategory ->
        TabRowItem(
            title = filterCategory,
            screen = {
                FilterListByCategory(
                    filters = filters.getListForCategory(filterCategory),
                    selectedFilters = filtersApplied.getListForCategory(filterCategory),
                    addFilter = { filtersApplied = filtersApplied.modifyOrAddItem(filterCategory, it, if (isSingleSelection) 0 else null) },
                    removeFilter = { filtersApplied = filtersApplied.removeItem(filterCategory, it) }
                )
            }
        )
    }
    val pagerState = rememberPagerState()

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 22.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Filter By",
                style = TextStyle(
                    fontSize = 21.sp,
                    lineHeight = 25.2.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2E384D),
                    letterSpacing = 0.15.sp,
                )
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.close),
                contentDescription = "close bottom sheet",
                modifier = Modifier.clickable { onCancelled() }
            )
        }
        BottomSheetTabLayout(tabRowItems = tabRowItems, pagerState = pagerState)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFF025284)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                enabled = filtersApplied.hasItems(),
                onClick = {
                    onFilterApplied(filtersApplied.clearAll())
                    onCancelled()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.padding(24.dp, 10.dp)
            ) {
                Text(
                    text = "Clear all",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF025284),
                        letterSpacing = 1.25.sp,
                    )
                )
            }
            Button(
                enabled = filtersApplied.hasItems(),
                onClick = { onFilterApplied(filtersApplied) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF025284)
                ),
                modifier = Modifier
                    .padding(24.dp, 10.dp)
                    .clip(RoundedCornerShape(100.dp))
            ) {
                Text(
                    text = "Apply",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        letterSpacing = 1.25.sp,
                    )
                )
            }
        }
    }
}

@Composable
fun FilterListByCategory(
    filters: List<String> = listOf(),
    selectedFilters: List<String>,
    addFilter: (String) -> Unit,
    removeFilter: (String) -> Unit,
) {
    VerticalGrid(
        columns = 2,
        items = filters,
        horizontalArrangement = Arrangement.SpaceBetween,
        columnSpacing = 24.dp,
        modifier = Modifier.padding(20.dp)
    ) { filter ->
        FilterCheckboxItem(
            isChecked = selectedFilters.contains(filter),
            filterText = filter
        ) { checked ->
            if (checked) addFilter(filter)
            else removeFilter(filter)
        }
    }
}

@Composable
fun FilterCheckboxItem(
    isChecked: Boolean,
    filterText: String,
    onFilterToggled: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.clickable { onFilterToggled(!isChecked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onFilterToggled(it) }
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = filterText,
            style = TextStyle(
                fontSize = 17.sp,
                lineHeight = 20.4.sp,
                color = Color(0xFF2E384D),
                letterSpacing = 0.15.sp,
            )
        )
    }
}