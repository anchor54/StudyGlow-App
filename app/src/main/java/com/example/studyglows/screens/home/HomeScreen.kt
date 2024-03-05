package com.example.studyglows.screens.home

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.auth.common.models.HomeUIEvent
import com.example.studyglows.screens.home.common.components.homeNavDrawerContent
import com.example.studyglows.shared.components.BaseScreenLayout
import com.example.studyglows.shared.components.FilterBottomSheet
import com.example.studyglows.shared.components.HomeScreenContent
import com.example.studyglows.shared.components.drawermenu.BaseDrawerNavigation
import com.example.studyglows.shared.viewmodels.SharedViewModel
import com.example.studyglows.utils.Utils.toShortenedString
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel,
    sharedViewModel: SharedViewModel,
    modifier: Modifier = Modifier
) {
    val filters by viewModel.filters.collectAsState()
    val filtersToApply by viewModel.selectedFilters.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    val searchResults by viewModel.searchResult.collectAsState()
    var showSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    val coursesNavController = rememberNavController()

    val screenId = navHostController.currentBackStackEntry?.arguments?.getString("screenId")

    LaunchedEffect(key1 = loading) {
        sharedViewModel.isLoading(loading)
    }

    LaunchedEffect(key1 = error) {
        sharedViewModel.showError(error)
    }

    LaunchedEffect(key1 = screenId) {
        when (screenId) {
            Screen.Explore.route -> coursesNavController.navigate(screenId)
            Screen.SavedCourses.route -> coursesNavController.navigate(screenId)
            else -> coursesNavController.navigate(Route.DASHBOARD_ROUTE.name)
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCategoryFilters()
        sharedViewModel.setDrawerMidOptions(
            options = homeNavDrawerContent(),
            handler = object : BaseDrawerNavigation(navHostController) {
                override fun handleDrawerNavigation(itemId: String) {
                    when (itemId) {
                        "my_courses" -> {}
                        "explore_courses" -> {}
                        "live_classes" -> {}
                        "saved_courses" -> {}
                        else -> super.handleDrawerNavigation(itemId)
                    }
                }
            }
        )
        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeUIEvent.NavigateCourseDetails -> {
                    val courseId = event.courseId
                    navHostController.navigate(
                        Screen.CourseDetails.route +
                                "?courseId=$courseId"
                    )
                }
                is HomeUIEvent.ShowFilters -> {
                    showSheet = true
                }
                else -> {}
            }
        }
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
                onCancelled = { coroutineScope.launch { showSheet = false } },
                onFilterApplied = {
                    viewModel.applyFilters(it)
                    coroutineScope.launch { showSheet = false }
                    coursesNavController.navigate(
                        Screen.FilterCourses.route +
                                "?title=${it.getAllItems().toShortenedString { it }}"
                    )
                }
            )
        }
    }
    BaseScreenLayout(
        openDrawer = { sharedViewModel.sendUIEvent(AppUIEvent.ShowDrawer) },
        modifier = modifier,
        searchResult = searchResults,
        onSearch = { viewModel.getSearchResults(it) },
        onSearchClicked = {
            coursesNavController.navigate(
                Screen.FilterCourses.route + "?title=$it"
            )
        },
        onResultItemClicked = {
            navHostController.navigate(
                Screen.CourseDetails.route + "?courseId=$it"
            )
        }
    ) {
        HomeScreenContent(
            viewModel = viewModel,
            navHostController = coursesNavController,
        )
    }
}