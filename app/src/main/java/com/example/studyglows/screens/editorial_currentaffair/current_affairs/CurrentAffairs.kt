package com.example.studyglows.screens.editorial_currentaffair.current_affairs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavHostController
import com.example.studyglows.R
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.editorial_currentaffair.component.editorialNavDrawerContent
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.component.CurrentAffairListItem
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairItem
import com.example.studyglows.screens.home.common.components.homeNavDrawerContent
import com.example.studyglows.shared.components.FilterBottomSheet
import com.example.studyglows.shared.components.FilterChip
import com.example.studyglows.shared.components.HomeAppBar
import com.example.studyglows.shared.components.drawermenu.BaseDrawerNavigation
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.example.studyglows.utils.Utils
import com.example.studyglows.utils.Utils.toShortenedString
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentAffairs(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    viewModel: CurrentAffairsViewModel
) {
    val currentAffairs by viewModel.currentAffairs.collectAsState()
    val filters by viewModel.filters.collectAsState()
    val filtersToApply by viewModel.selectedFilters.collectAsState()
    val currentAffairsGroupedByDate by remember(currentAffairs) {
        derivedStateOf {
            currentAffairs.groupBy { Utils.getDate(it.date, "EEEE, dd MMM yyyy") }
        }
    }
    var showSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCurrentAffairs()
        viewModel.getAllCategoryFilters()
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
                onCancelled = { showSheet = false },
                onAddFilter = { category, filter ->
                    viewModel.addFilter(category, filter)
                },
                onRemoveFilter = { category, filter ->
                    viewModel.removeFilter(category, filter)
                },
                onClearFilter = {
                    viewModel.clearFilterCategory("CURRENT_AFFAIRS")
                },
                onFilterApplied = {
                    viewModel.applyFilters()
                    showSheet = false
                }
            )
        }
    }

    Column(modifier = modifier.background(color = Color(0xFFE6F1F8))) {
        HomeAppBar(
            onNavIconClicked = { sharedViewModel.sendUIEvent(AppUIEvent.ShowDrawer()) },
            onSearchClicked = {}
        )
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 33.dp,
                topEnd = 33.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 19.dp, end = 19.dp, bottom = 8.dp, start = 19.dp)
            ) {
                if (filtersToApply.isNotEmpty()) {
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        filtersToApply.map { it.filterFields }.flatten().map { 
                            FilterChip(filterName = it) {
                                viewModel.removeFilter("CURRENT_AFFAIRS", it)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.calendar),
                    contentDescription = "filter by date"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.filter_icon),
                    contentDescription = "filter options",
                    modifier = Modifier.clickable { showSheet = true }
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                currentAffairsGroupedByDate.map { currentAffairGrp ->
                    item { CurrentAffairsGroupHeading(date = currentAffairGrp.key) }
                    items(currentAffairGrp.value.size) {
                        CurrentAffairListItem(
                            currentAffair = currentAffairGrp.value[it],
                            modifier = Modifier.clickable {
                                navHostController.navigate(
                                    Screen.CurrentAffairDetails.route +
                                            "/${currentAffairGrp.value[it].id}"
                                )
                            }
                        )
                    }
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
        modifier = Modifier.fillMaxWidth(),
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