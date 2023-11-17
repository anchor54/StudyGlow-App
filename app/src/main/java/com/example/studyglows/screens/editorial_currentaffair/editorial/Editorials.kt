package com.example.studyglows.screens.editorial_currentaffair.editorial

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studyglows.navigation.Route
import com.example.studyglows.navigation.Screen
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.screens.editorial_currentaffair.component.editorialNavDrawerContent
import com.example.studyglows.screens.editorial_currentaffair.editorial.component.EditorialContent
import com.example.studyglows.screens.editorial_currentaffair.editorial.component.EditorialListItem
import com.example.studyglows.shared.components.BaseScreenLayout
import com.example.studyglows.shared.components.drawermenu.BaseDrawerNavigation
import com.example.studyglows.shared.viewmodels.SharedViewModel

@Composable
fun Editorials(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    sharedVM: SharedViewModel,
    viewModel: EditorialViewModel
) {
    val editorialList by viewModel.editorials.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val searchResult by viewModel.searchResult.collectAsState()
    val topElement by remember(editorialList) {
        derivedStateOf {
            if (editorialList.isNotEmpty()) {
                val item = editorialList[0]
                editorialList.drop(1)
                item
            } else null
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getEditorialList()
        sharedVM.setDrawerMidOptions(
            options = editorialNavDrawerContent(),
            handler = object : BaseDrawerNavigation(navHostController) {
                override fun handleDrawerNavigation(itemId: String) {
                    when (itemId) {
                        "editorials" -> { navHostController.navigate(Route.EDITORIAL_ROUTE.name) }
                        "current_affairs" -> { navHostController.navigate(Route.CURRENT_AFFAIRS_ROUTE.name) }
                        "latest_vacancies" -> { navHostController.navigate(Screen.Vacancies.route) }
                        "saved_editorials" -> { navHostController.navigate(Screen.SavedVacancies.route) }
                        else -> super.handleDrawerNavigation(itemId)
                    }
                }
            }
        )
        viewModel.error.collect {
            sharedVM.showError(it)
        }
    }

    LaunchedEffect(key1 = loading) {
        sharedVM.isLoading(loading)
    }

    fun navigateToDetails(id: String) {
        navHostController.navigate("${Screen.EditorialDetails.route}/$id")
    }

    BaseScreenLayout(
        openDrawer = { sharedVM.sendUIEvent(AppUIEvent.ShowDrawer) },
        modifier = modifier,
        searchResult = searchResult,
        onSearch = { viewModel.getSearchResults(it) },
        onResultItemClicked = { navigateToDetails(it) }
    ) {
        EditorialContent(
            modifier = Modifier.fillMaxSize(),
            headerItem = topElement,
            onHeaderClicked = { navigateToDetails(topElement?.id ?: "") }
        ) {
            editorialList.map {
                EditorialListItem(
                    item = it,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { navigateToDetails(it.id) }
                )
            }
        }
    }
}