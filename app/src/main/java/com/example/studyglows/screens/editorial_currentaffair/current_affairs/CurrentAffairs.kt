package com.example.studyglows.screens.editorial_currentaffair.current_affairs

import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.component.CurrentAffairListItem
import com.example.studyglows.shared.components.BaseScreenLayout
import com.example.studyglows.shared.components.FilterBottomSheet
import com.example.studyglows.shared.components.FilterChip
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.example.studyglows.utils.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentAffairs(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    viewModel: CurrentAffairsViewModel
) {
    val calendar = Calendar.getInstance()
    val currentAffairs by viewModel.currentAffairs.collectAsState()
    val filters by viewModel.filters.collectAsState()
    val filtersToApply by viewModel.selectedFilters.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val searchResult by viewModel.searchResult.collectAsState()
    val currentAffairsGroupedByDate by remember(currentAffairs) {
        derivedStateOf {
            currentAffairs.groupBy { Utils.getDate(it.date, "EEEE, dd MMM yyyy") }
        }
    }
    var showSheet by remember { mutableStateOf(false) }
    var showDateFilter by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(calendar.timeInMillis) }
    var applyDateFilter by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(calendar.timeInMillis)
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var itemClickedId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCurrentAffairs()
        viewModel.getAllCategoryFilters()
        viewModel.error.collect {
            sharedViewModel.showError(it)
        }
    }

    LaunchedEffect(key1 = loading) {
        sharedViewModel.isLoading(loading)
    }
    LaunchedEffect(key1 = selectedDate) {
        viewModel.applyDateFilter(selectedDate)
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = bottomSheetState,
            dragHandle = null
        ) {
            FilterBottomSheet(
                filters = filters,
                selectedFilters = filtersToApply,
                isSingleSelection = true,
                onCancelled = { showSheet = false },
                onFilterApplied = {
                    viewModel.applyFilters(it)
                    showSheet = false
                }
            )
        }
    }

    if (showDateFilter) {
        DatePickerDialog(
            onDismissRequest = { showDateFilter = false },
            confirmButton = {
                TextButton(onClick = {
                    showDateFilter = false
                    applyDateFilter = true
                    selectedDate = datePickerState.selectedDateMillis!!
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDateFilter = false
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    itemClickedId?.let {
        Dialog(onDismissRequest = {}) {
            CurrentAffairDetails(
                viewModel = viewModel,
                modifier = Modifier.fillMaxSize(),
                sharedViewModel = sharedViewModel,
                currentAffairs = currentAffairs,
                itemId = currentAffairs[0].id
            ) { itemClickedId = null }
        }
    }

    BaseScreenLayout(
        openDrawer = { sharedViewModel.sendUIEvent(AppUIEvent.ShowDrawer) },
        modifier = modifier,
        searchResult = searchResult,
        onSearch = { viewModel.getSearchResults(it) },
        onSearchClicked = {},
        onResultItemClicked = { itemClickedId = it }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 19.dp, end = 19.dp, bottom = 8.dp, start = 19.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (filtersToApply.getAllItems().isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (applyDateFilter) {
                        FilterChip(filterName = Utils.getDate(selectedDate, "dd-MM-yyyy")) {
                            applyDateFilter = false
                        }
                    }
                    filtersToApply.getAllItems().map {
                        FilterChip(filterName = it) {
                            viewModel.removeFilter("CURRENT_AFFAIRS", it)
                        }
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { showDateFilter = true }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.calendar),
                    contentDescription = "filter by date"
                )
            }
            IconButton(onClick = { showSheet = true }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.filter_icon),
                    contentDescription = "filter options",
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            currentAffairsGroupedByDate.forEach { currentAffairGrp ->
                item { CurrentAffairsGroupHeading(date = currentAffairGrp.key) }
                items(currentAffairGrp.value.size) {
                    CurrentAffairListItem(
                        currentAffair = currentAffairGrp.value[it],
                        modifier = Modifier.clickable {
                            itemClickedId = currentAffairGrp.value[it].id
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CurrentAffairsGroupHeading(
    modifier: Modifier = Modifier,
    date: String
) {
    Row(
        modifier = modifier
            .padding(top = 32.dp, bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = date.split(",")[0],
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                color = Color(0xFF8798AD),
                letterSpacing = 0.4.sp,
            )
        )
        Box(modifier = Modifier
            .size(size = 5.dp)
            .clip(RoundedCornerShape(size = 5.dp))
            .background(color = Color(0xFF8798AD))
        )
        Text(
            text = date.split(",")[1].trim(),
            style = TextStyle(
                fontSize = 13.sp,
                lineHeight = 15.6.sp,
                color = Color(0xFF8798AD),
                letterSpacing = 0.4.sp,
            )
        )
    }
}